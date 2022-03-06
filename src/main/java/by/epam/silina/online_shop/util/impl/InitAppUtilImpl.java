package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.exception.FileUtilException;
import by.epam.silina.online_shop.exception.UserUtilException;
import by.epam.silina.online_shop.util.InitAppUtil;
import by.epam.silina.online_shop.view.View;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static by.epam.silina.online_shop.config.FileConstant.FILE_NAME_FEEDBACKS_DATA;

/**
 * Init app util with logic for preparing app before working with it.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitAppUtilImpl implements InitAppUtil {
    private static InitAppUtil instance;

    public static InitAppUtil getInstance() {
        if (instance == null) {
            instance = new InitAppUtilImpl();
        }
        return instance;
    }


    /**
     * Adds roles to datasource.
     * Adds users to datasource.
     * Adds items to datasource.
     * Adds order statuses to datasource.
     * Creates file for feedback data.
     * Run method doAction() of view layer.
     */
    public void initApp() {
        try {
            RoleUtilImpl.getInstance().addInitialRolesToDataSource();
            UserUtilImpl.getInstance().addInitialUsersToDataSource();
            ItemUtilImpl.getInstance().addInitialItemsToDataSource();
            OrderStatusUtilImpl.getInstance().addInitialOrderStatusesToDataSource();
            FileUtilImpl.getInstance().createFile(FILE_NAME_FEEDBACKS_DATA);
            View.getInstance().doAction();
        } catch (UserUtilException | FileUtilException e) {
            log.error("", e);
            EndAppUtilImpl.getInstance().endApp();
        }
    }
}
