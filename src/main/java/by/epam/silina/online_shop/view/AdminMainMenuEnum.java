package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.view.constant.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum AdminMainMenuEnum implements MenuIdentifier {
    ADMIN_INFO(SHOW_ADMIN_INFO),
    ORDERS(GO_TO_ORDERS_MENU),
    REVIEWS(SHOW_ALL_REVIEWS),
    LOGOUT(DO_LOGOUT);

    private final String command;
}