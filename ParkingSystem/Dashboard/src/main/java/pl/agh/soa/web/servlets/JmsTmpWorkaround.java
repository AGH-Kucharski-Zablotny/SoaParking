package pl.agh.soa.web.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.agh.soa.jms.dto.ParkGuardNotificationData;
import pl.agh.soa.web.PushNotifier;
import pl.agh.soa.web.controllers.SlotNotificationController;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Due to Jms context injection failure
 * (Caused by: javax.naming.NameNotFoundException: java:comp/TransactionSynchronizationRegistry)
 * there cannot be jms sending. :(
 */
@WebServlet(urlPatterns = {"/jmsNotification"}, asyncSupported = true, loadOnStartup = 1)
public class JmsTmpWorkaround extends HttpServlet {

    @EJB
    private PushNotifier pushNotifier;

    @Inject
    private SlotNotificationController slotNotificationController;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestObjectJson = req.getReader().lines().collect(Collectors.joining());
        if (requestObjectJson == null || requestObjectJson.isEmpty()) {
            pushNotifier.notifyListeners(null);
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ParkGuardNotificationData msgObj = objectMapper.readValue(requestObjectJson, ParkGuardNotificationData.class);
        slotNotificationController.addNewNotification(msgObj);
        pushNotifier.notifyListeners(msgObj);
    }
}
