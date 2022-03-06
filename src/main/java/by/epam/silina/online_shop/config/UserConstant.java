package by.epam.silina.online_shop.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConstant {
    public static final int USERNAME_MAX_LENGTH = 16;
    public static final int USERNAME_MIN_LENGTH = 2;

    public static final int PASSWORD_MAX_LENGTH = 20;
    public static final int PASSWORD_MIN_LENGTH = 8;

    public static final String REGEX_FOR_CHECKING_EMAIL = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static final String ADMIN_EMAIL = "admin@mail.ru";
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "11111111";
    public static final String CLIENT_EMAIL = "client@mail.ru";
    public static final String CLIENT_USERNAME = "client";
    public static final String CLIENT_PASSWORD = "11111111";
}
