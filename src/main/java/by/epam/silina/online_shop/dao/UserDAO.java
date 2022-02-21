package by.epam.silina.online_shop.dao;

import by.epam.silina.online_shop.model.User;

public interface UserDAO extends GenericDAO<User> {
    boolean isUserWithEmailPresent(String email);

    boolean isUserWithUsernamePresent(String username);
}
