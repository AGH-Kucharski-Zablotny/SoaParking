package pl.agh.soa.web.controllers;

import pl.agh.soa.ejb.services.TestService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("TestController")
public class TestController {
    @EJB(lookup = "java:global/EjbImplementation-1.0/TestServiceBean!pl.agh.soa.ejb.services.remote.TestServiceRemote")
    TestService testService;

    public String testConnection() {
        return testService.ping();
    }
}
