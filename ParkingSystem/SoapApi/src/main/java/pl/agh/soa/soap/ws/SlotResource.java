package pl.agh.soa.soap.ws;

import pl.agh.soa.exceptions.SlotOccupiedException;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SlotResource {
    @WebMethod
    void takeParkingSlot(Integer slotId, String registrationPlate) throws SlotOccupiedException;

    @WebMethod
    void releaseParkingSlot(Integer slotId);
}
