package pl.agh.soa.rest.endpoints;

import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class AccountResource {

    @EJB
    private AccountManagerRemote accountManager;

    @GET
    @Path("/employees/regions/{regionId}")
    public UserData getEmployeeForRegion(@PathParam("regionId") Integer regionId) {
        return accountManager.getAttendantForRegion(regionId);
    }
}
