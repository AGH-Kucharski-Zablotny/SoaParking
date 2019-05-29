package pl.agh.soa.ejb.dao;

import pl.agh.soa.dto.ParkingSlotData;

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
}
