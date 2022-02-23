package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.config.MenuIdentifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.view.constant.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum ClientMainMenuEnum implements MenuIdentifier {
    CLIENT_INFO(SHOW_CLIENT_INFO),
    ORDERS(SHOW_MY_ORDERS),
    CREATE_ORDER(CREATE_NEW_ORDER),
    FEEDBACKS(SHOW_MY_FEEDBACKS),
    FEEDBACK_ON_ONLINE_SHOP(LEAVE_FEEDBACK_ON_ONLINE_SHOP),
    FEEDBACK_ON_ITEM(LEAVE_FEEDBACK_ON_ITEM),
    LOGOUT(DO_LOGOUT);

    private final String command;
}