package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.ParkingSlotDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.ApplicationManager;
import pl.agh.soa.ejb.services.local.SlotManagerLocal;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.agh.soa.dto.ParkingSlotData.SlotStatus.EMPTY;
import static pl.agh.soa.dto.ParkingSlotData.SlotStatus.PARKED;

@Remote(SlotManagerRemote.class)
@Local(SlotManagerLocal.class)
@Singleton
@Startup
public class SlotManagerBean implements SlotManagerLocal, SlotManagerRemote
{
    @EJB(lookup = "java:global/ApplicationRouter-1.0/ApplicationManagerBean!pl.agh.soa.ejb.services.ApplicationManager")
    private ApplicationManager applicationManager;

    @PostConstruct
    public void init() {
        applicationManager.registerApplication(ApplicationManager.Application.SLOT_MANAGER, "http://localhost:8080/EjbSlotImpl-1.0/slots");
    }

    @PreDestroy
    public void destroy() {
        applicationManager.unregisterApplication(ApplicationManager.Application.SLOT_MANAGER);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addSlot(ParkingSlotData slot)
    {
        ParkingSlotDAO.getInstance().addItem(slot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updateSlot(ParkingSlotData slot)
    {
        ParkingSlotDAO.getInstance().updateItem(slot);
    }

    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ParkingSlotData getSlot(Integer slotId)
    {
        try {
            return ParkingSlotDAO.getInstance().getItem(slotId);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ParkingSlotData> getAllParkingSlots()
    {
        return ParkingSlotDAO.getInstance().getItems();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteSlot(ParkingSlotData slot)
    {
        ParkingSlotDAO.getInstance().deleteItem(slot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteSlot(Integer slotId)
    {
        ParkingSlotDAO.getInstance().deleteItem(slotId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ParkingSlotData> getParkingSlotsByStatus(String status)
    {
        List<ParkingSlotData> allSlots = ParkingSlotDAO.getInstance().getItems();
        return allSlots.stream().filter(c -> c.getStatus().contains(status)).collect(Collectors.toList());
    }
}
