package pl.agh.soa.dao;

import pl.agh.soa.dao.AbstractDAO;
import pl.agh.soa.dto.PaymentsData;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

public class PaymentsDAO extends AbstractDAO<PaymentsData> {

    private static PaymentsDAO instance;

    public static PaymentsDAO getInstance() {
        if (instance == null) {
            synchronized (PaymentsDAO.class) {
                if (instance == null) {
                    instance = new PaymentsDAO();
                }
            }
        }
        return instance;
    }

    private PaymentsDAO() {
        super(PaymentsData.class);
    }

    public PaymentsData getLatestPaymentForPark(int parkId) {
        TypedQuery<PaymentsData> query = entityManager.createQuery("SELECT data FROM PaymentsData data WHERE data.parksData.id = :parkId ORDER BY data.paymentData DESC", PaymentsData.class);
        query.setParameter("parkId", parkId);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        catch (NonUniqueResultException e) {
            return query.getResultList().get(0);
        }
    }
}

