package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.UserData;

import javax.xml.registry.infomodel.User;
import java.util.List;

public interface AccountManager {
    List<UserData> getAttendants();
    UserData getUser(Integer id);
    UserData getUserByUsernamePassword(String username, String password);
    void updateUser(UserData user);
    void createUser(UserData user);
    void deleteUser(Integer id);
    UserData getAttendantForRegion(Integer regionId);
    UserData getUserByLogin(String login);
}
