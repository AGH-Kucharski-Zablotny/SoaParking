package pl.agh.soa.rest.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class NewParkRequest implements Serializable {
    @NotNull
    @Min(value = 1, message = "slotId cannot be lower than 1")
    private Integer slotId;

    @NotNull
    private String registrationPlate;

    @NotNull
    private Date dateParked;

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public Date getDateParked() {
        return dateParked;
    }

    public void setDateParked(Date dateParked) {
        this.dateParked = dateParked;
    }
}
