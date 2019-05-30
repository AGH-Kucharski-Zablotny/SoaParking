package pl.agh.soa.ejb.services;

import java.util.Date;

public interface PaymentManager {
    void payForSlot(Integer slotId, Date dateBoughtTo, Date paymentDate, String registrationPlate);
}
