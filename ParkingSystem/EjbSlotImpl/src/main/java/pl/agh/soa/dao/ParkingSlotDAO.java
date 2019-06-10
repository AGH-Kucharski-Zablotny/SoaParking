package pl.agh.soa.dao;

import pl.agh.soa.dto.ParkingSlotData;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class ParkingSlotDAO extends AbstractDAO<ParkingSlotData> {

    private static ParkingSlotDAO instance;

    public static ParkingSlotDAO getInstance() {
        if (instance == null) {
            synchronized (ParkingSlotDAO.class) {
                if (instance == null) {
                    instance = new ParkingSlotDAO();
                }
            }
        }
        return instance;
    }

    private ParkingSlotDAO() {
        super(ParkingSlotData.class);
    }

    @Override
    public List<ParkingSlotData> getItems() {
        return super.getItems().stream().filter(s -> s.getDateRemoved() == null).collect(Collectors.toList());
    }

    @Override
    public ParkingSlotData getItem(Integer itemId) {
        TypedQuery query = entityManager.createQuery("SELECT c FROM ParkingSlotData c WHERE c.id = :id AND c.dateRemoved IS NULL", ParkingSlotData.class);
        query.setParameter("id", itemId);
        return (ParkingSlotData) query.getSingleResult();
    }

    @Override
    public void deleteItem(ParkingSlotData item) {
        item.setDateRemoved(new Date());
        updateItem(item);
    }
}
