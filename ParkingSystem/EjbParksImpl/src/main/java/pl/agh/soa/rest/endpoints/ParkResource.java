package pl.agh.soa.rest.endpoints;

import org.apache.http.HttpException;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.ParksManager;
import pl.agh.soa.exceptions.SlotOccupiedException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.registry.InvalidRequestException;

@Path("/parks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParkResource {

    @EJB(lookup = "java:global/EjbParksImpl-1.0/ParksManagerBean")
    private ParksManager parksManager;

    @GET
    @Path("/{slotId}/registrations/{registrationPlate}")
    public ParksData getParks(@PathParam("slotId") Integer slotId,
                                    @PathParam("registrationPlate") String registrationPlate) {
        ParksData park = parksManager.getLatestParkForData(registrationPlate, slotId);
        if (park == null) {
            throw new NotFoundException();
        }
        return park;
    }

    @POST
    public String newPark(ParksData data) {
        try {
            parksManager.parkFreeSlot(data.getParkingSlotData().getId(), data.getRegistrationPlate(), data.getDateParked());
        } catch (SlotOccupiedException e) {
            return "Slot is already occupied";
        }
        return "Success";
    }
}
