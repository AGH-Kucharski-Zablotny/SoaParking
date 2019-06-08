package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.ParksManager;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
}
