package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.rest.payment.PayRequest;
import pl.agh.soa.ejb.services.PaymentManager;
import pl.agh.soa.ejb.services.local.PaymentManagerLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class PaymentResource {

    @EJB(lookup = "java:global/EjbPaymentImpl-1.0/PaymentManagerBean!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManager paymentManager;

    @Path("slots/{slotId}")
    @POST
    public void payForSlot(@PathParam("slotId") int slotId, PayRequest payRequest) {
        paymentManager.payForSlot(slotId, payRequest.getDateBoughtTo(), payRequest.getPaymentDate(), payRequest.getRegistrationPlate());
    }
}
