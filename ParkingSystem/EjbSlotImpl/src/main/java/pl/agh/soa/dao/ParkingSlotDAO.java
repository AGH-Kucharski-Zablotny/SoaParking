package pl.agh.soa.dao;

import pl.agh.soa.dto.ParkingSlotData;

import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Queue;

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
    public void updateItem(ParkingSlotData item) {
//        ParkingSlotData entity = entityManager.find(ParkingSlotData.class, item.getId());
//        if (!entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().begin();
//        }
//        entity.setStatus(item.getStatus());
//        entityManager.lock(entity, LockModeType.PESSIMISTIC_WRITE);
//        entityManager.getTransaction().commit();

//        if (entityManager.getTransaction().isActive()) {
//            entityManager.flush();
//            entityManager.clear();
//        }
//
//        entityManager.close();
//        entityManager = Persistence
//                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
//                .createEntityManager();

//        entityManager.getTransaction().begin();
//        ParkingSlotData entity = entityManager.find(ParkingSlotData.class, item.getId());
//        Query q = entityManager.createQuery("UPDATE ParkingSlotData data SET data.status = :status where data.id = :id");
//        q.setParameter("status", item.getStatus());
//        q.setParameter("id", entity.getId());
//        q.executeUpdate();
//        entityManager.getTransaction().commit();
        super.updateItem(item);
    }
}
