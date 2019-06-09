package pl.agh.soa.dao;

import pl.agh.soa.dto.ParksData;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class ParksDAO extends AbstractDAO<ParksData> {

    private static ParksDAO instance;

    public static ParksDAO getInstance() {
        if (instance == null) {
            synchronized (ParksDAO.class) {
                if (instance == null) {
                    instance = new ParksDAO();
                }
            }
        }
        return instance;
    }

    private ParksDAO() {
        super(ParksData.class);
    }

    public ParksData getLatestParkForData(Integer slotId) {
        TypedQuery<ParksData> query =
                entityManager.createQuery("SELECT data FROM ParksData data WHERE data.dateLeft IS NULL AND " +
                        "data.parkingSlotData.id = :slotId " +
                        "ORDER BY data.dateParked DESC", ParksData.class);
//        query.setParameter("registrationPlate", registrationPlate);
        query.setParameter("slotId", slotId);
        query.setMaxResults(1);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
