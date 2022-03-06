package by.epam.silina.online_shop.view.menu;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.config.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum InitialMenuEnum implements MenuIdentifier {
    REGISTRATION(REGISTER_NEW_USER),
    LOGIN(DO_LOGIN),
    EXIT(DO_EXIT);

    private final String command;
}
