package by.epam.silina.online_shop.util;

import by.epam.silina.online_shop.view.View;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.lang.System.exit;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InitAndEndAppUtil {
    private static final InitAndEndAppUtil instance = new InitAndEndAppUtil();
    private final View view = View.getInstance();

    public static InitAndEndAppUtil getInstance() {
        return instance;
    }

    public void initApp() {
        view.doAction();
    }

    public void endApp() {
        exit(0);
    }
}
