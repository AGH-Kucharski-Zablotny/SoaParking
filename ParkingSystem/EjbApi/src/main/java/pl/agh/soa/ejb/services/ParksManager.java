package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.exceptions.SlotOccupiedException;

import java.util.Date;

public interface ParksManager
{
    ParksData getLatestParkForData(Integer slotId);
    ParksData getLatestParkForData(String registrationPlate);
    void parkFreeSlot(Integer slotId, String registrationPlate, Date dateParked) throws SlotOccupiedException;
    void releaseParkingSlot(Integer slotId);
}
