package by.epam.silina.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.constant.MenuConstant.*;

@Getter
@AllArgsConstructor
public enum MenuParamEnum {
    REGISTRATION(ONE, "registration"),
    LOGIN(TWO, "login"),
    SHOW_ALL_USERS(THREE, "show all users"),
    EXIT(FOUR, "exit");

    private final int number;
    private final String command;
}
