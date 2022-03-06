package by.epam.silina.online_shop.util;

import by.epam.silina.online_shop.exception.UserUtilException;

public interface UserUtil {
    void addInitialUsersToDataSource() throws UserUtilException;
}
