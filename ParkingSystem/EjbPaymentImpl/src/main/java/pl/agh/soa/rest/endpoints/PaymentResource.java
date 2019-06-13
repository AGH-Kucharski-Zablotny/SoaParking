package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.dto.RatesData;
import pl.agh.soa.dto.rest.payment.PayRequest;
import pl.agh.soa.ejb.services.PaymentManager;
import pl.agh.soa.ejb.services.local.PaymentManagerLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/payment")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PaymentResource {

    @EJB(lookup = "java:global/EjbPaymentImpl-1.0/PaymentManagerBean!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManager paymentManager;

    @Path("slots/{slotId}")
    @POST
    public void payForSlot(PayRequest payRequest) {
        paymentManager.payForSlot(payRequest.getDateBoughtTo(), payRequest.getPaymentDate(), payRequest.getRegistrationPlate());
    }

    @Path("scheduler")
    @POST
    public void scheduleCheck(ParksData data) {
        paymentManager.scheduleParkPaymentCheck(data);
    }

    @POST
    @Path("rates")
    public void addValuationRate(RatesData data) {
        paymentManager.setRate(data.getAmount(), data.getHours());
    }
}
