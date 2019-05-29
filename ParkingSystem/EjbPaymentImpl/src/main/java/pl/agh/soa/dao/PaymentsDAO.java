package pl.agh.soa.dao;

import pl.agh.soa.dao.AbstractDAO;
import pl.agh.soa.dto.PaymentsData;

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
}
