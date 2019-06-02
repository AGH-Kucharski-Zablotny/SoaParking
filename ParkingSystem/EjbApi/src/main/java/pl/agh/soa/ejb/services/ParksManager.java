package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.exceptions.SlotOccupiedException;

import java.util.Date;

public interface ParksManager
{
    ParksData getLatestParkForData(String registrationPlate, Integer slotId);
    void parkFreeSlot(Integer slotId, String registrationPlate, Date dateParked) throws SlotOccupiedException;
    void releaseParkingSlot(Integer slotId, String registrationPlate);
}
