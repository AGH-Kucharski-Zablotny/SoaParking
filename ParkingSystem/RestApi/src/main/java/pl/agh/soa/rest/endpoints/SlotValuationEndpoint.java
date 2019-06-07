package pl.agh.soa.rest.endpoints;

import pl.agh.soa.ejb.services.remote.PaymentManagerRemote;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/slotvaluation")
public class SlotValuationEndpoint
{
    @EJB(lookup = "java:global/EjbParksImpl-1.0/PaymentMenagerRemote!pl.agh.soa.ejb.services.remote.PaymentManagerRemote")
    private PaymentManagerRemote paymentManager;

    @GET
    @Produces("application/json")
    @Path("/date/{dateTo}")
    public Response getSlotValuationByDate(@PathParam("dateTo") Date datePrice)
    {
        // response status ok
        return Response.status(200).entity(paymentManager.getPriceForDate(datePrice)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/hours/{hours}")
    public Response getSlotValuationByHours(@PathParam("hours") Long hours)
    {
        // response status ok
        return Response.status(200).entity(paymentManager.getPriceForHours(hours)).build();
    }
}
