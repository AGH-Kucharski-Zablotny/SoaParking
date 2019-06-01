package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.PaymentsDAO;
import pl.agh.soa.dao.RatesDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.dto.RatesData;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.ejb.*;
import javax.persistence.NoResultException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Remote(PaymentManagerRemote.class)
@Stateless
public class PaymentManagerBean implements PaymentManagerRemote {

    @EJB(lookup = "java:global/EjbParksImpl-1.0/ParksManagerBean!pl.agh.soa.ejb.services.remote.ParksManagerRemote")
    private ParksManagerRemote parksManager;

    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.remote.SlotManagerRemote")
    private SlotManagerRemote slotManager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void payForSlot(Integer slotId, Date dateBoughtTo, Date paymentDate, String registrationPlate) {
        ParksData parkToBePayed = parksManager.getLatestParkForData(registrationPlate, slotId);

        PaymentsData payment = prepareNewPayment(dateBoughtTo, paymentDate, parkToBePayed);
        PaymentsDAO.getInstance().addItem(payment);

        ParkingSlotData slot = slotManager.getSlot(slotId);
        slot.setStatus(ParkingSlotData.SlotStatus.PARKED);
        slotManager.updateSlot(slot);
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
        RatesData rateSuitableForHours = rates.stream().min(compareByHoursDifference(hours)).get();
        return rateSuitableForHours.getAmount();
    }

    private Comparator<RatesData> compareByHoursDifference(Long hours) {
        return (r1, r2) -> {
            float diffR1 = Math.abs(r1.getAmount() - hours);
            float diffR2 = Math.abs(r2.getAmount() - hours);
            return Float.compare(diffR1, diffR2);
        };
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
}
