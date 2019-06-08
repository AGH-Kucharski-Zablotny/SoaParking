package pl.agh.soa.dto.rest.payment;

import java.util.Date;

public class PayRequest {
    private Date dateBoughtTo;
    private Date paymentDate;
    private String registrationPlate;

    public Date getDateBoughtTo() {
        return dateBoughtTo;
    }

    public void setDateBoughtTo(Date dateBoughtTo) {
        this.dateBoughtTo = dateBoughtTo;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }
}
