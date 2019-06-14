package pl.agh.soa.dao;

import pl.agh.soa.dto.UserData;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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

    public UserData getUserByLoginPassword(String login, String password)
    {
        TypedQuery query = entityManager.createQuery("SELECT u FROM UserData u WHERE u.login = :login AND u.password = :password", UserData.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (UserData) query.getSingleResult();
    }

    public UserData getAttendantForRegion(Integer regionId) {
        TypedQuery<UserData> query =
                entityManager.createQuery(
                        "SELECT data FROM UserData data WHERE data.role = :role AND data.region = :regionId",
                        UserData.class);
        query.setParameter("role", UserData.Roles.EMPLOYEE);
        query.setParameter("regionId", regionId);
        return query.getSingleResult();
    }

    public UserData getUserByLogin(String login) {
        TypedQuery<UserData> query =
                entityManager.createQuery(
                        "SELECT data FROM UserData data WHERE data.login = :login",
                        UserData.class);
        query.setParameter("login", login);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
