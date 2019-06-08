package pl.agh.soa.jms.dto;

import java.io.Serializable;
import java.util.Date;

public class ParkGuardNotificationData implements Serializable {
    private int slotId;
    private String registrationPlate;
    private Date notificationDate;

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }
}
