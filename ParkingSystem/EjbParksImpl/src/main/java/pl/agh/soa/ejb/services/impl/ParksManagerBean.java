package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.ParksDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.ApplicationManager;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;
import pl.agh.soa.exceptions.SlotOccupiedException;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.Date;

@Remote(ParksManagerRemote.class)
@Startup
@Singleton
public class ParksManagerBean implements ParksManagerRemote
{

    @EJB(lookup = "java:global/ApplicationRouter-1.0/ApplicationManagerBean!pl.agh.soa.ejb.services.ApplicationManager")
    private ApplicationManager applicationManager;

    @PostConstruct
    public void init() {
        applicationManager.registerApplication(ApplicationManager.Application.PARKS_MANAGER, "http://localhost:8080/EjbParksImpl-1.0/parks");
    }

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
//        ParkingSlotData parkingSlot = slotManager.getSlot(slotId);
//        if(parkingSlot.getStatus().equals("PARKED"))
//        {
//            throw new SlotOccupiedException();
//        }
//        ParksData park = prepareNewPark(parkingSlot, registrationPlate, dateParked);
//        ParksDAO.getInstance().addItem(park);
//        parkingSlot.setStatus(PARKED);
//        slotManager.updateSlot(parkingSlot);
//        paymentManager.scheduleParkPaymentCheck(park);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void releaseParkingSlot(Integer slotId, String registrationPlate)
    {
//        ParksData park = parksManager.getLatestParkForData(registrationPlate, slotId);
//        park.setDateLeft(new Date());
//        ParksDAO.getInstance().updateItem(park);
//        ParkingSlotData parkingSlot = slotManager.getSlot(slotId);
//        parkingSlot.setStatus(EMPTY);
//        slotManager.updateSlot(parkingSlot);
    }

    @Override
    public ParksData getLatestParkForData(String registrationPlate, Integer slotId) {
        return ParksDAO.getInstance().getLatestParkForData(registrationPlate, slotId);
    }
}
