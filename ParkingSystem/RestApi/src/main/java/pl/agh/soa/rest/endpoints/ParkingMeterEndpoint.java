package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.PaymentsData;
import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

public class ParkingMeterEndpoint
{
    @EJB(lookup = "java:global/EjbParksImpl-1.0/PaymentMenagerRemote!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManagerRemote paymentManager;

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/platnosc")
    public Response createPayment(PaymentsData payment, Integer slotId)
    {
        try
        {
            paymentManager.payForSlot(slotId, payment.getDateBoughtTo(), new Date(), payment.getParksData().getRegistrationPlate());
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
