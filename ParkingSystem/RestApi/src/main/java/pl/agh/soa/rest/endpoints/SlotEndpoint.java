package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/slots")
@RolesAllowed({UserData.Roles.ADMIN, UserData.Roles.EMPLOYEE})
public class SlotEndpoint
{
    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.remote.SlotManagerRemote")
    private SlotManagerRemote slotManager;

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getSlot(@PathParam("id") int slotId)
    {
        ParkingSlotData slot = slotManager.getSlot(slotId);
        if (slot != null) {
            // response status ok
            return Response.status(200).entity(slot).build();
        }
        else {
            return Response.status(404).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getSlotsByStatus(@QueryParam("status") String status)
    {
        if (status == null) {
            return Response.status(200).entity(slotManager.getAllParkingSlots()).build();
        }
        // response status ok
        return Response.status(200).entity(slotManager.getParkingSlotsByStatus(status)).build();
    }
}
