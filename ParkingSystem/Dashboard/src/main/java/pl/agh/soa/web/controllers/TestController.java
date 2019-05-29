package pl.agh.soa.web.controllers;

import pl.agh.soa.ejb.services.TestService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named("TestController")
public class TestController {
    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.local.SlotManagerLocal")
    TestService testService;

    public String testConnection() {
        return testService.ping();
    }
}
