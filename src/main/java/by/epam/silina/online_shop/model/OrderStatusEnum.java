package by.epam.silina.online_shop.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatusEnum {
    WAITING_FOR_PAYMENT("WAITING FOR PAYMENT"),
    PAID("PAID"),
    CONFIRMED("CONFIRMED"),
    COMPLETED("COMPLETED"),
    DECLINED_BY_ADMIN("DECLINED BY ADMIN"),
    CANCELED_BY_CLIENT("CANCELED BY CLIENT");

    private final String status;
}
