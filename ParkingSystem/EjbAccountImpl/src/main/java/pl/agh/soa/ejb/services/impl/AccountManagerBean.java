package pl.agh.soa.ejb.services.impl;

import pl.agh.soa.dao.UsersDAO;
import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.xml.registry.infomodel.User;
import java.util.List;
import java.util.stream.Collectors;

@Remote(AccountManagerRemote.class)
@Stateless
public class AccountManagerBean implements AccountManagerRemote {
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

}
