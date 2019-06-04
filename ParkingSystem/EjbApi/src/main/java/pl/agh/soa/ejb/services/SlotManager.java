package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.ParkingSlotData;

import java.util.List;

public interface SlotManager
{
    void addSlot(ParkingSlotData slot);
    void updateSlot(ParkingSlotData slot);
    ParkingSlotData getSlot(Integer slotId);
    List<ParkingSlotData> getAllParkingSlots();
    void deleteSlot(ParkingSlotData slot);
    void deleteSlot(Integer slotId);
}
