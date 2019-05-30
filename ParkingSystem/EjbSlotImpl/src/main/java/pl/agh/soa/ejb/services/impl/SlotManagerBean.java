package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.ejb.services.local.SlotManagerLocal;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(SlotManagerRemote.class)
@Local(SlotManagerLocal.class)
@Stateless
public class SlotManagerBean implements SlotManagerLocal, SlotManagerRemote {
    @Override
    public String testest() {
        return "asdasd";
    }

    @Override
    public ParkingSlotData getSlot(Integer slotId) {
        return null;
    }

    @Override
    public void updateSlot(ParkingSlotData slot) {

    }
}
