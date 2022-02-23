package by.epam.silina.online_shop.util;

import by.epam.silina.online_shop.view.View;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitAppUtil {
    private static InitAppUtil instance;
    private final View view = View.getInstance();

    public static InitAppUtil getInstance() {
        if (instance == null) {
            instance = new InitAppUtil();
        }
        return instance;
    }

    public void initApp() {
        view.doAction();
    }
}
