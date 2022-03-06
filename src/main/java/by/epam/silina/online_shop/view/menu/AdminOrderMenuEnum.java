package by.epam.silina.online_shop.view.menu;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.config.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum AdminOrderMenuEnum implements MenuIdentifier {
    SHOW_ORDERS(SHOW_ALL_ORDERS),
    CHANGE_ORDER_STATUS(CHANGE_ORDER_STATUS_BY_ID),
    GO_BACK(GO_BACK_TO_PREVIOUS_MENU),
    LOGOUT(DO_LOGOUT);

    private final String command;
}
