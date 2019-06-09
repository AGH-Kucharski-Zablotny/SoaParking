package pl.agh.soa.rest.endpoints;

import org.apache.http.HttpException;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.ParksManager;
import pl.agh.soa.exceptions.SlotOccupiedException;
import pl.agh.soa.rest.dto.NewParkRequest;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import javax.xml.registry.InvalidRequestException;

@Path("/parks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParkResource {

    @EJB(lookup = "java:global/EjbParksImpl-1.0/ParksManagerBean")
    private ParksManager parksManager;

    @GET
    @Path("/{slotId}")
    public ParksData getLatestPark(@PathParam("slotId") Integer slotId) {
        ParksData park = parksManager.getLatestParkForData(slotId);
        if (park == null) {
            throw new NotFoundException();
        }
        return park;
    }

    @POST
    public void newPark(NewParkRequest data) {
        try {
            parksManager.parkFreeSlot(data.getSlotId(), data.getRegistrationPlate(), data.getDateParked());
        } catch (SlotOccupiedException e) {
            throw new ClientErrorException("Slot is already occupied", Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("slot/{slotId}")
    public void releaseSlot(@PathParam("slotId") Integer slotId) {
        parksManager.releaseParkingSlot(slotId);
    }

}
