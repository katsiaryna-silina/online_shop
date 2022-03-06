package by.epam.silina.online_shop.service;

import by.epam.silina.online_shop.model.OrderStatus;

public interface OrderStatusService {
    OrderStatus getOrderStatusByName(String orderStatusName);
}
