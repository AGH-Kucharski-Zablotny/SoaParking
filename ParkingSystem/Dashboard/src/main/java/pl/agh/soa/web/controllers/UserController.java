package pl.agh.soa.web.controllers;

import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("UserController")
@SessionScoped
public class UserController implements Serializable {

    @EJB(lookup = "java:global/EjbAccountImpl-1.0/AccountManagerBean!pl.agh.soa.ejb.services.remote.AccountManagerRemote")
    private AccountManagerRemote accountManager;

    private UserData user;

    private UserData userToBeAdded = new UserData();


    public UserData getUser() {
         return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public List<UserData> getAttendants() {
        return accountManager.getAttendants();
    }

    public boolean isUserAdmin() {
        return user != null && UserData.Roles.ADMIN.equals(user.getRole());
    }

    public UserData getUserToBeAdded() {
        return userToBeAdded;
    }

    public void setUserToBeAdded(UserData userToBeAdded) {
        this.userToBeAdded = userToBeAdded;
    }

    public void addEmployee() {
        userToBeAdded.setRole(UserData.Roles.EMPLOYEE);
        accountManager.createUser(userToBeAdded);
        userToBeAdded = new UserData();
    }

    public List<String> listRoles() {
        return Arrays.asList(UserData.Roles.ADMIN, UserData.Roles.EMPLOYEE);
    }

    public void updateUser() {
        accountManager.updateUser(user);
        user = accountManager.getUser(user.getId());
    }

    public void loginUser(String login) {
        if (user == null) {
            user = accountManager.getUserByLogin(login);
        }
    }
}
