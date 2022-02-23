package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.view.constant.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum ClientOrderMenuEnum implements MenuIdentifier {
    ALL_ITEMS(SHOW_ALL_GOODS_TO_PURCHASE),
    ADD_ITEM(ADD_ITEM_TO_ORDER),
    REMOVE_ITEM(REMOVE_ITEM_FROM_ORDER),
    REMOVE_ALL_ITEMS(REMOVE_ALL_ITEMS_FROM_ORDER),
    GO_BACK(CLEAN_THE_ORDER_AND_RETURN_TO_THE_PREVIOUS_MENU),
    LOGOUT(DO_LOGOUT);

    private final String command;
}
