package pl.agh.soa.dao;

import pl.agh.soa.dto.RatesData;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class RatesDAO extends AbstractDAO<RatesData> {

    private static RatesDAO instance;

    private RatesDAO() {
        super(RatesData.class);
    }

    public static RatesDAO getInstance() {
        if (instance == null) {
            synchronized (RatesDAO.class) {
                if (instance == null) {
                    instance = new RatesDAO();
                }
            }
        }
        return instance;
    }

    public RatesData getHourlyRate(Integer hours) {
        TypedQuery<RatesData> query = entityManager.createQuery("SELECT data FROM RatesData data WHERE data.hours = :hours", RatesData.class);
        query.setParameter("hours", hours);
        return query.getSingleResult();
    }
}
