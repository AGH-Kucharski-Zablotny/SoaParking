package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.ParksDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;
import pl.agh.soa.exceptions.SlotOccupiedException;

import javax.ejb.*;
import java.util.Date;

import static pl.agh.soa.dto.ParkingSlotData.SlotStatus.PARKED;

@Remote(ParksManagerRemote.class)
@Stateless
public class ParksManagerBean implements ParksManagerRemote
{
    @EJB(lookup = "java:global/EjbSlotImpl-1.0/SlotManagerBean!pl.agh.soa.ejb.services.remote.SlotManagerRemote")
    private SlotManagerRemote slotManager;

    private ParksData prepareNewPark(ParkingSlotData parkingSlot, String registrationPlate, Date dateParked)
    {
        ParksData park = new ParksData();
        park.setParkingSlotData(parkingSlot);
        park.setRegistrationPlate(registrationPlate);
        park.setDateParked(dateParked);
        return park;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void parkFreeSlot(Integer slotId, String registrationPlate, Date dateParked) throws SlotOccupiedException
    {
        ParkingSlotData parkingSlot = slotManager.getSlot(slotId);
        if(parkingSlot.getStatus().equals("PARKED"))
        {
            throw new SlotOccupiedException();
        }
            ParksData park = prepareNewPark(parkingSlot, registrationPlate, dateParked);
            ParksDAO.getInstance().addItem(park);
            parkingSlot.setStatus(PARKED);
            slotManager.updateSlot(parkingSlot);
    }

    @Override
    public ParksData getLatestParkForData(String registrationPlate, Integer slotId) {
        return null;
    }
}
