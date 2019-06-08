package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.ParksData;

import java.util.Date;

public interface PaymentManager {
    void payForSlot(Integer slotId, Date dateBoughtTo, Date paymentDate, String registrationPlate);
    void setRate(Float value, Integer hourAmount);
    Float getPriceForHours(Long hours);
    Float getPriceForDate(Date dateToPark);
    void deleteRate(Integer hours);
    void setTimeToPay(long time);
    long getTimeToPay();
    void scheduleParkPaymentCheck(ParksData parkInfo);
    void sendParkNotPayed(ParksData parkInfo);
}
