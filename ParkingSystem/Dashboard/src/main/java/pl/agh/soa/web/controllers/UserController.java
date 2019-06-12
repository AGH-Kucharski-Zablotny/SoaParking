package pl.agh.soa.web.controllers;

import pl.agh.soa.dto.UserData;
import pl.agh.soa.ejb.services.remote.AccountManagerRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("UserController")
@SessionScoped
public class UserController implements Serializable {

    @EJB(lookup = "java:global/EjbAccountImpl-1.0/AccountManagerBean!pl.agh.soa.ejb.services.remote.AccountManagerRemote")
    private AccountManagerRemote accountManager;

    private UserData user;

    @PostConstruct
    public void init() {
        user = accountManager.getUser(1);
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public List<UserData> getAttendants() {
        return accountManager.getAttendants();
    }
}
