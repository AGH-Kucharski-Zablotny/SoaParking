package pl.agh.soa.ejb.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import pl.agh.soa.dao.ParksDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.ApplicationManager;
import pl.agh.soa.ejb.services.remote.ParksManagerRemote;
import pl.agh.soa.exceptions.SlotOccupiedException;
import pl.agh.soa.rest.RestClient;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.persistence.NoResultException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.NotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static pl.agh.soa.dto.ParkingSlotData.SlotStatus.EMPTY;
import static pl.agh.soa.dto.ParkingSlotData.SlotStatus.PARKED;

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

    @PreDestroy
    public void destroy() {
        applicationManager.unregisterApplication(ApplicationManager.Application.PARKS_MANAGER);
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
        try {
            String slotUrl = applicationManager.getApplicationUrl(ApplicationManager.Application.SLOT_MANAGER) + "/" + slotId;
            ParkingSlotData parkingSlot = RestClient.sendRequest(RestClient.prepareRequest(HttpMethod.GET, slotUrl), ParkingSlotData.class);
            if(parkingSlot == null || parkingSlot.getStatus().equals(PARKED))
            {
                throw new SlotOccupiedException();
            }
            ParksData park = prepareNewPark(parkingSlot, registrationPlate, dateParked);
            ParksDAO.getInstance().addItem(park);
            parkingSlot.setStatus(ParkingSlotData.SlotStatus.TAKEN);
            RestClient.sendRequest(RestClient.prepareRequest(HttpMethod.PUT, slotUrl, parkingSlot), ParkingSlotData.class);
            String schedulerUrl = applicationManager.getApplicationUrl(ApplicationManager.Application.PAYMENT_MANAGER) + "/scheduler";
            HttpRequestBase request = null;
            try {
                request = RestClient.prepareRequest(HttpMethod.POST, schedulerUrl, park);
            } catch (JsonProcessingException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            RestClient.sendRequest(request, ParksData.class);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void releaseParkingSlot(Integer slotId)
    {
        try {
            ParksData park = getLatestParkForData(slotId);
            if (park == null) {
                throw new NoResultException();
            }
            park.setDateLeft(new Date());
            ParksDAO.getInstance().updateItem(park);
            String slotUrl = applicationManager.getApplicationUrl(ApplicationManager.Application.SLOT_MANAGER) + "/" + slotId;
            ParkingSlotData parkingSlot = RestClient.sendRequest(RestClient.prepareRequest(HttpGet.METHOD_NAME, slotUrl), ParkingSlotData.class);
            if (parkingSlot == null) {
                throw new NotFoundException();
            }
            parkingSlot.setStatus(EMPTY);
            RestClient.sendRequest(RestClient.prepareRequest(HttpMethod.PUT, slotUrl, parkingSlot), ParkingSlotData.class);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    @Override
    public ParksData getLatestParkForData(Integer slotId) {
        return ParksDAO.getInstance().getLatestParkForData(slotId);
    }

    @Override
    public ParksData getLatestParkForData(String registrationPlate) {
        return ParksDAO.getInstance().getLatestParkForData(registrationPlate);
    }
}
