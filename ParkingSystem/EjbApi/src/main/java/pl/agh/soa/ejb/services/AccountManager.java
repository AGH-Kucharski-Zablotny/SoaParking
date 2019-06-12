package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.UserData;

import java.util.List;

public interface AccountManager {
    List<UserData> getAttendants();
    UserData getUser(Integer id);
    void updateUser(UserData user);
    void createUser(UserData user);
    void deleteUser(Integer id);
}
