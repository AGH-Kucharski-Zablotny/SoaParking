package pl.agh.soa.ejb.services;

import pl.agh.soa.dto.UserData;

public interface AccountManager {
    UserData getUser(Integer id);
    void updateUser(UserData user);
    void createUser(UserData user);
    void deleteUser(Integer id);
}
