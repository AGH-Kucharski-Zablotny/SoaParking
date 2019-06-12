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

@Named("SlotNotificationController")
@ApplicationScoped
public class SlotNotificationController implements Serializable {
    private List<ParkGuardNotificationData> attendantNotifications = new ArrayList<>();

    public void addNewNotification(ParkGuardNotificationData data) {
        attendantNotifications.add(data);
    }

    public ParkGuardNotificationData getNotificationForGuard(Integer attendantId) {
//        attendantNotifications.stream().filter(n -> n.get)
        return null;
    }

    public ParkGuardNotificationData getNotificationForSlot(Integer slotId) {
        return attendantNotifications.stream()
                .filter(n -> n.getSlotId() == slotId)
                .min(Comparator.comparing(ParkGuardNotificationData::getNotificationDate))
                .orElse(new ParkGuardNotificationData());
    }
}
