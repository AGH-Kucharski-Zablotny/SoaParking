package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.UsersDAO;
import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.ApplicationManager;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Remote(AccountManagerRemote.class)
@Singleton
@Startup
public class AccountManagerBean implements AccountManagerRemote {

    @EJB(lookup = "java:global/ApplicationRouter-1.0/ApplicationManagerBean!pl.agh.soa.ejb.services.ApplicationManager")
    private ApplicationManager applicationManager;

    @PostConstruct
    public void init() {
        applicationManager.registerApplication(ApplicationManager.Application.ACCOUNT_MANAGER, "http://localhost:8080/EjbAccountImpl-1.0/accounts");
    }

    @Override
    public UserData getUser(Integer id) {
        try {
            return UsersDAO.getInstance().getItem(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserData getUserByUsernamePassword(String username, String password)
    {
        try
        {
            return UsersDAO.getInstance().getUserByLoginPassword(username, password);
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public void updateUser(UserData user) {
        UsersDAO.getInstance().updateItem(user);
    }

    @Override
    public void createUser(UserData user) {
        UsersDAO.getInstance().addItem(user);
    }

    @Override
    public List<UserData> getAttendants() {
        return UsersDAO.getInstance().getItems().stream().filter(u -> u.getRegion() != null).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        UsersDAO.getInstance().deleteItem(id);
    }

    public UserData getAttendantForRegion(Integer regionId) {
        return UsersDAO.getInstance().getAttendantForRegion(regionId);
    }

    @Override
    public UserData getUserByLogin(String login) {
        return UsersDAO.getInstance().getUserByLogin(login);
    }
}
