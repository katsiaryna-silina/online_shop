package by.epam.silina.online_shop.service;

import by.epam.silina.online_shop.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getAllOrders();

    List<Order> getOrdersByUserId(Long userId);

    boolean createOrderWithItems(Map<Long, Integer> itemIdToNumberOfItemsMap);
}
