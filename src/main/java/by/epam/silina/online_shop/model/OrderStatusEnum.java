package by.epam.silina.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.config.OrderStatusConstant.*;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    WAITING_FOR_PAYMENT_STATUS(WAITING_FOR_PAYMENT),
    PAID_STATUS(PAID),
    CONFIRMED_STATUS(CONFIRMED),
    COMPLETED_STATUS(COMPLETED),
    DECLINED_BY_ADMIN_STATUS(DECLINED_BY_ADMIN),
    CANCELED_BY_CLIENT_STATUS(CANCELED_BY_CLIENT);

    private final String orderStatusDescription;
}
