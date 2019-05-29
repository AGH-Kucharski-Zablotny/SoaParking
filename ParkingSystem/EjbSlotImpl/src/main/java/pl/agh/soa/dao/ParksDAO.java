package pl.agh.soa.dao;

import pl.agh.soa.dto.ParksData;

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
}
