package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.dao.impl.UserDAOImpl;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.service.RoleService;
import by.epam.silina.online_shop.service.UserService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

import static by.epam.silina.online_shop.config.RoleConstant.CLIENT;

/**
 * Service for user logic responsible for processing user data from dao layer.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    private static final UserService instance = new UserServiceImpl();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final RoleService roleService = RoleServiceImpl.getInstance();

    public static UserService getInstance() {
        return instance;
    }


    /**
     * Requests data of all user objects from user dao and returns list of them.
     *
     * @return List<User>
     */
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    /**
     * Creates user object from list of parameters for registration.
     * Checks if there is a user with username in datasource and saves created user.
     * Returns boolean value whether user with passed parameters has been saved to datasource.
     *
     * @param paramsForRegistration list of parameters for registration
     */
    @Override
    public boolean registerUser(List<String> paramsForRegistration) {
        var email = paramsForRegistration.get(0);
        var username = paramsForRegistration.get(1);
        if (userDAO.isUserWithEmailPresent(email) || userDAO.isUserWithUsernamePresent(username)) {
            return false;
        }
        var password = paramsForRegistration.get(2);
        var user = User.builder()
                .uniqueNumber(UUID.randomUUID())
                .role(roleService.getRoleByName(CLIENT))
                .email(email)
                .username(username)
                .password(password)
                .build();
        userDAO.save(user);
        log.info("User with id={} has been registered.", user.getId());
        return true;
    }

    /**
     * Requests data of user from user dao using passed parameters for login.
     * Checks if there is a user with passed parameters in datasource and returns this user.
     *
     * @param paramsForLogin list of parameters for login
     * @return User
     */
    @Override
    public User login(List<String> paramsForLogin) {
        var username = paramsForLogin.get(0);
        var user = userDAO.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            var password = paramsForLogin.get(1);
            if (!isUserPasswordCorrect(user, password)) {
                return null;
            }
            return user;
        }
    }

    /**
     * Requests number of users from user dao using getEntityCount() method and returns this number.
     *
     * @return Long
     */
    @Override
    public Long getUserCount() {
        return userDAO.getEntityCount();
    }

    /**
     * Requests user info passed user object, processes it and returns string of processed user info.
     *
     * @param user user object
     * @return String
     */
    @Override
    public String getUserInfo(User user) {
        if (user == null) {
            log.error("User cannot be null");
            return "";
        } else {
            return "User info. username: " + user.getUsername() + ", email: " + user.getEmail();
        }
    }

    private boolean isUserPasswordCorrect(User user, String password) {
        return user.getPassword().equals(password);
    }
}
