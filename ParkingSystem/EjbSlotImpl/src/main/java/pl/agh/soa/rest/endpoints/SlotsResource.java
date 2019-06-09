package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.ejb.services.SlotManager;
import pl.agh.soa.ejb.services.local.SlotManagerLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/slots")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class SlotsResource {

    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.remote.SlotManagerRemote")
    private SlotManager slotManager;

    @POST
    public void addSlot(ParkingSlotData slotData) {
        slotManager.addSlot(slotData);
    }

    @GET
    @Path("/{id}")
    public ParkingSlotData getParkingSlot(@PathParam("id") Integer slotId) {
        ParkingSlotData slot = slotManager.getSlot(slotId);
        if (slot == null) {
            throw new NotFoundException();
        }
        return slot;
    }

    @PUT
    @Path("/{id}")
    public void updateSlot(@PathParam("id") Integer slotId, String status) {
        ParkingSlotData slotData = new ParkingSlotData();
        slotData.setId(slotId);
        slotData.setStatus(status);
        slotManager.updateSlot(slotData);
    }
}
