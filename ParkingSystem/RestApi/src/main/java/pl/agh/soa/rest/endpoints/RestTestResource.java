package pl.agh.soa.rest.endpoints;

import pl.agh.soa.ejb.services.TestService;
import pl.agh.soa.ejb.services.remote.TestServiceRemote;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/system/status")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestTestResource {

    @EJB(lookup = "java:global/EjbPaymentImpl-1.0/TestServiceBean!pl.agh.soa.ejb.services.remote.TestServiceRemote")
    TestServiceRemote testService;

    @GET
    public String getStatus() {
        return testService.ping();
    }
}
