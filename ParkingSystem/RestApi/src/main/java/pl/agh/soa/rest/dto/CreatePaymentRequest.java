package pl.agh.soa.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class CreatePaymentRequest implements Serializable {
    @NotNull
    private Date dateBoughtTo;

    @NotNull
    private Date paymentDate;

    @NotNull
    @NotBlank
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
