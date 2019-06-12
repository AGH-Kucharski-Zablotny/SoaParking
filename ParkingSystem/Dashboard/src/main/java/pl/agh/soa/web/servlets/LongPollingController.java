package pl.agh.soa.web.servlets;

import pl.agh.soa.web.PushNotifier;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@WebServlet(urlPatterns = {"/pushPoll"}, asyncSupported = true, loadOnStartup = 1)
public class LongPollingController extends HttpServlet {
    @EJB
    private PushNotifier pushNotifier;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(MediaType.TEXT_PLAIN);
        resp.setStatus(202);
        resp.setHeader("Pragma", "no-cache");
        resp.setCharacterEncoding("UTF-8");
        resp.flushBuffer();

        final AsyncContext asyncContext = req.startAsync();
        pushNotifier.addPeer(asyncContext);
    }
}
