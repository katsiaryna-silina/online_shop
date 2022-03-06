package by.epam.silina.online_shop.view.menu;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.config.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum AdminMainMenuEnum implements MenuIdentifier {
    ADMIN_INFO(SHOW_ADMIN_INFO),
    USERS(SHOW_ALL_USERS),
    ORDERS(GO_TO_ORDERS_MENU),
    FEEDBACKS(SHOW_ALL_FEEDBACKS),
    FEEDBACKS_FROM_FILE(SHOW_FEEDBACKS_FROM_FILE),
    LOGOUT(DO_LOGOUT);

    private final String command;
}