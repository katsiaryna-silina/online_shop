package by.epam.silina.online_shop.view.menu;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.config.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum ClientMainMenuEnum implements MenuIdentifier {
    CLIENT_INFO(SHOW_CLIENT_INFO),
    ALL_ITEMS_SORTED_BY_DEFAULT(SHOW_ALL_ITEMS_SORTED_BY_DEFAULT),
    ALL_ITEMS_SORTED_BY_PRICE_LOW_TO_HIGH(SHOW_ALL_ITEMS_SORTED_BY_PRICE_LOW_TO_HIGH),
    ALL_ITEMS_SORTED_BY_PRICE_HIGH_TO_LOW(SHOW_ALL_ITEMS_SORTED_BY_PRICE_HIGH_TO_LOW),
    ORDERS(SHOW_MY_ORDERS),
    CREATE_ORDER(CREATE_NEW_ORDER),
    FEEDBACKS(SHOW_MY_FEEDBACKS),
    FEEDBACK_ON_ONLINE_SHOP(LEAVE_FEEDBACK_ON_ONLINE_SHOP),
    DELETE_FEEDBACK(DELETE_FEEDBACK_ON_ONLINE_SHOP),
    LOGOUT(DO_LOGOUT);

    private final String command;
}