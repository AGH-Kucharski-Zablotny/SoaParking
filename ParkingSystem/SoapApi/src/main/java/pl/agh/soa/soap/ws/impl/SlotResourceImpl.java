package pl.agh.soa.soap.ws.impl;

import pl.agh.soa.soap.ws.SlotResource;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "pl.agh.soa.soap.ws.SlotResource")
public class SlotResourceImpl implements SlotResource {

    @WebMethod
    @Override
    public void takeParkingSlot(Integer slotId, String registrationPlate) {
        // insert
    }

    @WebMethod
    @Override
    public void releaseParkingSlot(Integer slotId) {

    }
}
