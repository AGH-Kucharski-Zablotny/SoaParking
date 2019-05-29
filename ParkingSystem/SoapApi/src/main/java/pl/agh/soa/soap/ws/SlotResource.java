package pl.agh.soa.soap.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SlotResource {
    @WebMethod
    void takeParkingSlot(Integer slotId, String registrationPlate);

    @WebMethod
    void releaseParkingSlot(Integer slotId);
}
