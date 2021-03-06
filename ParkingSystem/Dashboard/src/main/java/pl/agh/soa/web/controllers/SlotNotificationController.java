package pl.agh.soa.web.controllers;

import pl.agh.soa.jms.dto.ParkGuardNotificationData;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Named("SlotNotificationController")
@ApplicationScoped
public class SlotNotificationController implements Serializable {
    private List<ParkGuardNotificationData> attendantNotifications = new ArrayList<>();

    public void addNewNotification(ParkGuardNotificationData data) {
        attendantNotifications.add(data);
    }

    public List<ParkGuardNotificationData> getNotificationsForGuard(Integer guardId) {
        return attendantNotifications.stream()
                .filter(n -> n.getGuardId().equals(guardId))
                .sorted(Comparator.comparing(ParkGuardNotificationData::getNotificationDate).reversed())
                .collect(Collectors.toList());
    }

    public ParkGuardNotificationData getLatestNotificationForGuard(Integer employeeId) {
        return attendantNotifications.stream()
                .filter(n -> n.getGuardId().equals(employeeId))
                .max(Comparator.comparing(ParkGuardNotificationData::getNotificationDate))
                .orElse(new ParkGuardNotificationData());
    }

    public String displayNotificationText(Integer guardId) {
        ParkGuardNotificationData lastNotification = getLatestNotificationForGuard(guardId);
        if (lastNotification.getRegistrationPlate() != null) {
            return "Car with registration number " +
                    lastNotification.getRegistrationPlate() +
                    " is parked without payment on slot " +
                    lastNotification.getSlotId() +
                    ". Go and check that car.";
        } else {
            return "";
        }
    }

    public String displayNotificationText(ParkGuardNotificationData notificationData) {
        if (notificationData.getRegistrationPlate() != null) {
            return "Car with registration number " +
                    notificationData.getRegistrationPlate() +
                    " is parked without payment on slot " +
                    notificationData.getSlotId() +
                    ". Go and check that car.";
        } else {
            return "";
        }
    }


    public ParkGuardNotificationData getNotificationForSlot(Integer slotId) {
        return attendantNotifications.stream()
                .filter(n -> n.getSlotId() == slotId)
                .min(Comparator.comparing(ParkGuardNotificationData::getNotificationDate))
                .orElse(new ParkGuardNotificationData());
    }
}
