package pl.agh.soa.web.controllers;

import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("SlotController")
@SessionScoped
public class SlotController implements Serializable {

    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.remote.SlotManagerRemote")
    private SlotManagerRemote slotManager;

    public List<ParkingSlotData> getItems() {
        return slotManager.getAllParkingSlots();
    }

    public String mapStatus(String statusCode) {
        switch (statusCode) {
            case ParkingSlotData.SlotStatus.EMPTY:
                return "Empty";
            case ParkingSlotData.SlotStatus.PARKED:
                return "Parked";
            case ParkingSlotData.SlotStatus.TAKEN:
                return "Taken";
            case ParkingSlotData.SlotStatus.OVERDUE:
                return "Overdue";
            default:
                return "Unknown";
        }
    }
}
