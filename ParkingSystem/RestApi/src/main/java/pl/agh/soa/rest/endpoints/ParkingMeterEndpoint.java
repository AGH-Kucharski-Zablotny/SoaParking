package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;
import pl.agh.soa.rest.dto.CreatePaymentRequest;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/payment")
public class ParkingMeterEndpoint
{
    @EJB(lookup = "java:global/EjbPaymentImpl-1.0/PaymentManagerBean!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManagerRemote paymentManager;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createPayment(@Valid CreatePaymentRequest paymentRequest)
    {
        try
        {
            paymentManager.payForSlot(paymentRequest.getDateBoughtTo(), paymentRequest.getPaymentDate(), paymentRequest.getRegistrationPlate());
        }
        catch(NullPointerException e)
        {
            // response status bad request
            Response.status(400).entity("Parking slot with given ID isn't occupied!").build();
        }
        catch(Exception e)
        {
            // response status internal server error
            Response.status(500).build();
        }
        // response status ok
        return Response.status(200).build();
    }
}
