package pl.agh.soa.dao;

import pl.agh.soa.dto.UserData;

public class UsersDAO extends AbstractDAO<UserData> {

    private static UsersDAO instance;

    public static UsersDAO getInstance() {
        if (instance == null) {
            synchronized (UsersDAO.class) {
                if (instance == null) {
                    instance = new UsersDAO();
                }
            }
        }
        return instance;
    }

    private UsersDAO() {
        super(UserData.class);
    }
}
