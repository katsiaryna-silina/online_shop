package by.epam.silina.online_shop.dao;

import by.epam.silina.online_shop.exception.OrderDAOException;
import by.epam.silina.online_shop.model.Order;

import java.util.List;

public interface OrderDAO extends GenericDAO<Order> {
    List<Order> getOrdersByUserId(Long userId) throws OrderDAOException;
}
