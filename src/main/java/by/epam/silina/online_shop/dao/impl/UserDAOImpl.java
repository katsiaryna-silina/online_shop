package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.model.User;

/**
 * User dao implementation responsible for getting user's data from datasource.
 */
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {
    private static final UserDAO instance = new UserDAOImpl();

    private UserDAOImpl() {
        super(User.class);
    }

    public static UserDAO getInstance() {
        return instance;
    }

    /**
     * Depending on email, requests data of all users and returns boolean value
     * whether there is the user with appropriate email presents in the datasource.
     *
     * @param email string of email
     * @return boolean if user with passed email present
     */
    @Override
    public boolean isUserWithEmailPresent(String email) {
        return getAll().stream()
                .map(User::getEmail)
                .anyMatch(email::equals);
    }

    /**
     * Depending on username, requests data of all users and returns boolean value
     * whether there is the user with appropriate username presents in the datasource.
     *
     * @param username string of username
     * @return boolean if user with passed username present
     */
    @Override
    public boolean isUserWithUsernamePresent(String username) {
        return getAll().stream()
                .map(User::getUsername)
                .anyMatch(username::equals);
    }

    /**
     * Depending on username, requests data of all users and returns user with appropriate username.
     *
     * @param username string of username
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        return getAll().stream()
                .filter(el -> el.getUsername().equals(username))
                .findFirst().orElse(null);
    }
}
