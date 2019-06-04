package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.ParkingSlotDAO;
import pl.agh.soa.dto.ParkingSlotData;
import pl.agh.soa.dto.ParksData;
import pl.agh.soa.ejb.services.local.SlotManagerLocal;
import pl.agh.soa.ejb.services.remote.SlotManagerRemote;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.DatabaseMetaData;
import java.util.Date;
import java.util.List;

@Remote(SlotManagerRemote.class)
@Local(SlotManagerLocal.class)
@Stateless
public class SlotManagerBean implements SlotManagerLocal, SlotManagerRemote
{
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
    public ParkingSlotData getSlot(Integer slotId)
    {
        return ParkingSlotDAO.getInstance().getItem(slotId);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<ParkingSlotData> getAllParkingSlots()
    {
        return ParkingSlotDAO.getInstance().getItems();
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteSlot(ParkingSlotData slot)
    {
        ParkingSlotDAO.getInstance().deleteItem(slot);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteSlot(Integer slotId)
    {
        ParkingSlotDAO.getInstance().deleteItem(slotId);
    }
}
