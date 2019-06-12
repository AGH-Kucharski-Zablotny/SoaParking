package pl.agh.soa.web.jms;

import pl.agh.soa.jms.dto.ParkGuardNotificationData;
import pl.agh.soa.web.PushNotifier;
import pl.agh.soa.web.controllers.SlotNotificationController;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.io.Serializable;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/exported/jms/topic/ParkingSystemTopic"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class SendAttendantListener implements MessageListener {

    @EJB
    private PushNotifier pushNotifier;

    @Inject
    private SlotNotificationController slotNotificationController;

    @Override
    public void onMessage(Message message) {
        try {
            Serializable msgObj = ((ObjectMessage) message).getObject();
            if (msgObj instanceof ParkGuardNotificationData) {
                slotNotificationController.addNewNotification((ParkGuardNotificationData) msgObj);
                pushNotifier.notifyParkNotPayed((ParkGuardNotificationData) msgObj);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
