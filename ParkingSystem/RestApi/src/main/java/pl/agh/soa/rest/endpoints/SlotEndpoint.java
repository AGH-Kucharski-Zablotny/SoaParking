package pl.agh.soa.rest.endpoints;

import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/slots")
public class SlotEndpoint
{
    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.remote.SlotManagerRemote")
    private SlotManagerRemote slotManager;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getSlot(@PathParam("id") int slotId)
    {
        // response status ok
        return Response.status(200).entity(slotManager.getSlot(slotId)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/")
    public Response getFreeSlots()
    {
        // response status ok
        return Response.status(200).entity(slotManager.getParkingSlotsByStatus("EMPTY")).build();
    }

    @GET
    @Produces("application/json")
    @Path("/")
    public Response getOccupiedSlots()
    {
        // response status ok
        return Response.status(200).entity(slotManager.getParkingSlotsByStatus("PARKED")).build();
    }
}
