package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.dao.RoleDAO;
import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.dao.impl.RoleDAOImpl;
import by.epam.silina.online_shop.dao.impl.UserDAOImpl;
import by.epam.silina.online_shop.exception.FileUtilException;
import by.epam.silina.online_shop.exception.UserUtilException;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.util.FileUtil;
import by.epam.silina.online_shop.util.UserUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.epam.silina.online_shop.config.FileConstant.FILE_NAME_USERS_DATA;
import static by.epam.silina.online_shop.config.RoleConstant.ADMIN;
import static by.epam.silina.online_shop.config.RoleConstant.CLIENT;
import static by.epam.silina.online_shop.config.UserConstant.*;

/**
 * User util with logic for creation initial users.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtilImpl implements UserUtil {
    private static final UserUtil instance = new UserUtilImpl();
    private final FileUtil fileUtil = FileUtilImpl.getInstance();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final RoleDAO roleDAO = RoleDAOImpl.getInstance();

    public static UserUtil getInstance() {
        return instance;
    }

    /**
     * Checks if file with user objects is empty fills this file with users using method createInitialUsers(),
     * reads user objects from this file and adds them to datasource.
     * If file is not empty just reads user objects from this file and adds them to datasource.
     */
    public void addInitialUsersToDataSource() throws UserUtilException {
        try {
            File file = fileUtil.createFile(FILE_NAME_USERS_DATA);
            if (fileUtil.isFileEmpty(file)) {
                log.info("File {} is empty. Trying to write initial user's data to file", FILE_NAME_USERS_DATA);
                var initialUsers = createInitialUsers();
                fileUtil.writeUserListToFile(initialUsers, file);
            } else {
                log.info("File {} already contains initial user's data. Trying to add them into memory data source", FILE_NAME_USERS_DATA);
            }
            var usersFromFile = fileUtil.readUserListFromFile(file);
            usersFromFile.forEach(userDAO::save);
        } catch (FileUtilException e) {
            log.error("", e);
            throw new UserUtilException(e);
        }
    }

    private List<User> createInitialUsers() {
        List<User> users = new ArrayList<>();
        User admin = User.builder()
                .email(ADMIN_EMAIL)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .role(roleDAO.getRoleByName(ADMIN))
                .uniqueNumber(UUID.randomUUID())
                .build();
        users.add(admin);
        User client = User.builder()
                .email(CLIENT_EMAIL)
                .username(CLIENT_USERNAME)
                .password(CLIENT_PASSWORD)
                .role(roleDAO.getRoleByName(CLIENT))
                .uniqueNumber(UUID.randomUUID())
                .build();
        users.add(client);
        return users;
    }
}
