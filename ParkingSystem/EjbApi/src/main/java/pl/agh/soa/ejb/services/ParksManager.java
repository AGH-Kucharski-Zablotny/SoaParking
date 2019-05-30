package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.ParksData;

public interface ParksManager {
    ParksData getLatestParkForData(String registrationPlate, Integer slotId);
}
