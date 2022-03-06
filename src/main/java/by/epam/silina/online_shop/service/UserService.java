package by.epam.silina.online_shop.service;

import by.epam.silina.online_shop.model.User;

import java.util.List;

public interface UserService {
    boolean registerUser(List<String> paramsForRegistration);

    User login(List<String> paramsForLogin);

    String getUserInfo(User user);

    List<User> getAllUsers();

    Long getUserCount();
}
