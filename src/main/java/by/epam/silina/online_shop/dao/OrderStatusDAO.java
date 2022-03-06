package by.epam.silina.online_shop.dao;

import by.epam.silina.online_shop.model.OrderStatus;

public interface OrderStatusDAO extends GenericDAO<OrderStatus> {
    OrderStatus getOrderStatusByName(String orderStatusName);
}
