package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.ParkingSlotData;

public interface SlotManager {
    String testest();
    ParkingSlotData getSlot(Integer slotId);
    void updateSlot(ParkingSlotData slot);
}
