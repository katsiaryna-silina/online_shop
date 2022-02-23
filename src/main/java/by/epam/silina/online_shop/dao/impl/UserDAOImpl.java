package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.model.User;

public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    private static final UserDAOImpl instance = new UserDAOImpl();

    private UserDAOImpl() {
        super(User.class);
    }

    public static UserDAOImpl getInstance() {
        return instance;
    }

    @Override
    public boolean isUserWithEmailPresent(String email) {
        return getAll().values().stream()
                .map(User::getEmail)
                .anyMatch(email::equals);
    }

    @Override
    public boolean isUserWithUsernamePresent(String username) {
        return getAll().values().stream()
                .map(User::getUsername)
                .anyMatch(username::equals);
    }

    @Override
    public User getUserByUsername(String username) {
        return getAll().values().stream()
                .filter(el -> el.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}
