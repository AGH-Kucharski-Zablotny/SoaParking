package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(ParksManagerRemote.class)
@Stateless
public class ParksManagerBean implements ParksManagerRemote {
    @Override
    public ParksData getLatestParkForData(String registrationPlate, Integer slotId) {
        return null;
    }
}
