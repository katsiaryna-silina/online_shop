package by.epam.silina.online_shop.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderStatusConstant {
    public static final String WAITING_FOR_PAYMENT = "WAITING FOR PAYMENT";
    public static final String PAID = "PAID";
    public static final String CONFIRMED = "CONFIRMED";
    public static final String COMPLETED = "COMPLETED";
    public static final String DECLINED_BY_ADMIN = "DECLINED BY ADMIN";
    public static final String CANCELED_BY_CLIENT = "CANCELED BY CLIENT";
}
