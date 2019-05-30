package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.PaymentsDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.ejb.*;
import java.util.Date;

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
}
