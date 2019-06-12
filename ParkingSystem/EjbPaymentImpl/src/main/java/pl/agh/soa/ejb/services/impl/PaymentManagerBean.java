package pl.agh.soa.ejb.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import pl.agh.soa.dao.PaymentsDAO;
import pl.agh.soa.dao.RatesDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.dto.RatesData;
import pl.agh.soa.ejb.services.ApplicationManager;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;
import pl.agh.soa.jms.dto.ParkGuardNotificationData;
import pl.agh.soa.rest.RestClient;
import pl.agh.soa.tasks.PaymentCheckTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import javax.persistence.NoResultException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.Timer;
import java.util.stream.Collectors;

@Remote(PaymentManagerRemote.class)
@Singleton
@Startup
public class PaymentManagerBean implements PaymentManagerRemote {

    @EJB(lookup = "java:global/ApplicationRouter-1.0/ApplicationManagerBean!pl.agh.soa.ejb.services.ApplicationManager")
    private ApplicationManager applicationManager;

    @Inject
    private JMSContext context;

    @Resource(lookup = "java:jboss/exported/jms/topic/ParkingSystemTopic")
    private Topic topic;

    private Long timeToPay = 5000L;

    @PostConstruct
    public void init() {
        applicationManager.registerApplication(ApplicationManager.Application.PAYMENT_MANAGER, "http://localhost:8080/EjbPaymentImpl-1.0/payment");
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void payForSlot(Date dateBoughtTo, Date paymentDate, String registrationPlate) {
        try {
            String parksUrl = applicationManager.getApplicationUrl(ApplicationManager.Application.PARKS_MANAGER) + "/registration/" + registrationPlate;
            ParksData parkToBePayed = RestClient.sendRequest(RestClient.prepareRequest(HttpGet.METHOD_NAME, parksUrl), ParksData.class);
            if (parkToBePayed == null) {
                throw new NotFoundException();
            }

            PaymentsData payment = prepareNewPayment(dateBoughtTo, paymentDate, parkToBePayed);
            PaymentsDAO.getInstance().addItem(payment);

            String slotUrl = applicationManager.getApplicationUrl(ApplicationManager.Application.SLOT_MANAGER) + "/" + parkToBePayed.getParkingSlotData().getId();
            ParkingSlotData slot = RestClient.sendRequest(RestClient.prepareRequest(HttpGet.METHOD_NAME, slotUrl), ParkingSlotData.class);
            if (slot == null) {
                throw new NotFoundException();
            }
            slot.setStatus(ParkingSlotData.SlotStatus.PARKED);
            RestClient.sendRequest(RestClient.prepareRequest(HttpPut.METHOD_NAME, slotUrl, slot), ParkingSlotData.class);
            scheduleOverdueCheck(parkToBePayed, payment);

        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private PaymentsData prepareNewPayment(Date dateBoughtTo, Date paymentDate, ParksData parkToBePayed) {
        PaymentsData payment = new PaymentsData();
        payment.setDateBoughtTo(dateBoughtTo);
        payment.setPaymentDate(paymentDate != null ? paymentDate : new Date());
        payment.setParksData(parkToBePayed);
        return payment;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void setRate(Float value, Integer hourAmount) {
        try {
            RatesData existingRate = RatesDAO.getInstance().getHourlyRate(hourAmount);
            existingRate.setAmount(value);
            RatesDAO.getInstance().updateItem(existingRate);
        }
        catch (NoResultException e) {
            RatesData newRate = prepareNewRate(value, hourAmount);
            RatesDAO.getInstance().addItem(newRate);
        }
    }

    private RatesData prepareNewRate(Float value, Integer hourAmount) {
        RatesData newRate = new RatesData();
        newRate.setAmount(value);
        newRate.setHours(hourAmount);
        return newRate;
    }

    @Override
    public Float getPriceForHours(Long hours) throws NoResultException {
        List<RatesData> rates = RatesDAO.getInstance().getItems();
        List<RatesData> ratesSuitableForHours = rates.stream().filter(r -> r.getHours() <= hours).collect(Collectors.toList());
        if (ratesSuitableForHours.size() == 0) {
            return 0F;
        }
        return ratesSuitableForHours.get(ratesSuitableForHours.size() - 1).getAmount();
    }

    @Override
    public Float getPriceForDate(Date dateToPark) {
        Period period = Period.between(LocalDate.now(), LocalDate.from(dateToPark.toInstant()));
        return getPriceForHours(period.get(ChronoUnit.HOURS));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteRate(Integer hours) {
        RatesData rateToBeRemoved = RatesDAO.getInstance().getHourlyRate(hours);
        RatesDAO.getInstance().deleteItem(rateToBeRemoved);
    }

    @Override
    public synchronized void setTimeToPay(long time) {
        timeToPay = time;
    }

    @Override
    public long getTimeToPay() {
        return timeToPay;
    }

    @Override
    public void scheduleParkPaymentCheck(ParksData parkInfo) {
        PaymentCheckTask paymentCheckTask = new PaymentCheckTask(parkInfo, this);
        Timer timer = new Timer();
        timer.schedule(paymentCheckTask, timeToPay);
    }

    @Override
    public void sendParkNotPayed(ParksData parkInfo) {
        ParkGuardNotificationData notification = new ParkGuardNotificationData();
        notification.setSlotId(parkInfo.getParkingSlotData().getId());
        notification.setRegistrationPlate(parkInfo.getRegistrationPlate());
        notification.setNotificationDate(new Date());
        context.createProducer().send(topic, notification);
    }

    @Override
    public Date getDateParkedTo(Integer parkId) {
        return PaymentsDAO.getInstance().getLatestPaymentForPark(parkId).getDateBoughtTo();
    }

    private void scheduleOverdueCheck(ParksData parkInfo, PaymentsData paymentsData) {
        PaymentCheckTask paymentCheckTask = new PaymentCheckTask(parkInfo, this);
        Timer timer = new Timer();
        Date currentDate = new Date();
        long checkOffset = paymentsData.getDateBoughtTo().getTime() - currentDate.getTime() + timeToPay;
        timer.schedule(paymentCheckTask, checkOffset);
    }
}
