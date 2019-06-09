package pl.agh.soa.soap.ws.impl;

import pl.agh.soa.ejb.services.ParksManager;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;
import pl.agh.soa.exceptions.SlotOccupiedException;
import pl.agh.soa.soap.ws.SlotResource;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Date;

@Stateless
@WebService(endpointInterface = "pl.agh.soa.soap.ws.SlotResource")
public class SlotResourceImpl implements SlotResource {

    @EJB(lookup = "java:global/EjbParksImpl-1.0/ParksManagerBean!pl.agh.soa.ejb.services.remote.ParksManagerRemote")
    private ParksManagerRemote parksManager;

    @Override
    public void takeParkingSlot(Integer slotId, String registrationPlate) throws SlotOccupiedException {
        parksManager.parkFreeSlot(slotId, registrationPlate, new Date());
    }

    @Override
    public void releaseParkingSlot(Integer slotId) {
        if (slotId != null) {
            parksManager.releaseParkingSlot(slotId);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
