package pl.agh.soa.web.controllers;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("ParksController")
@SessionScoped
public class ParksController implements Serializable {
    @EJB(lookup = "java:global/EjbParksImpl-1.0/ParksManagerBean!pl.agh.soa.ejb.services.remote.ParksManagerRemote")
    private ParksManagerRemote parksManager;

    public ParksData getParksDataForSlot(Integer slotId) {
        ParksData park = parksManager.getLatestParkForData(slotId);
        return park != null ? park : new ParksData();
    }
}
