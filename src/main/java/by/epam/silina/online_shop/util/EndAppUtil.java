package by.epam.silina.online_shop.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.lang.System.exit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EndAppUtil {
    private static EndAppUtil instance;

    public static EndAppUtil getInstance() {
        if (instance == null) {
            instance = new EndAppUtil();
        }
        return instance;
    }

    public void endApp() {
        exit(0);
    }
}
