package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.util.EndAppUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.lang.System.exit;

/**
 * End app util for logic to end app.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndAppUtilImpl implements EndAppUtil {
    private static EndAppUtil instance;

    public static EndAppUtil getInstance() {
        if (instance == null) {
            instance = new EndAppUtilImpl();
        }
        return instance;
    }

    /**
     * Ends app using System exit(0) method.
     */
    public void endApp() {
        exit(0);
    }
}
