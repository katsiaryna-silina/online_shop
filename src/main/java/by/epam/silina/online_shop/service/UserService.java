package by.epam.silina.online_shop.service;

import by.epam.silina.online_shop.model.Role;

public interface UserService {
    void register();

    //todo return Role? or User?
    Role login();

    void showAllUsers();
}
