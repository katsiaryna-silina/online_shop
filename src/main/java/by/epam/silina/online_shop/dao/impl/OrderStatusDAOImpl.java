package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.OrderStatusDAO;
import by.epam.silina.online_shop.model.OrderStatus;

/**
 * Order status dao implementation responsible for getting order status's data from datasource.
 */
public class OrderStatusDAOImpl extends GenericDAOImpl<OrderStatus> implements OrderStatusDAO {
    private static final OrderStatusDAO instance = new OrderStatusDAOImpl();

    private OrderStatusDAOImpl() {
        super(OrderStatus.class);
    }

    public static OrderStatusDAO getInstance() {
        return instance;
    }

    /**
     * Depending on order status name, requests data of all order statuses and returns appropriate order status.
     *
     * @param orderStatusName string of order status name
     * @return OrderStatus
     */
    @Override
    public OrderStatus getOrderStatusByName(String orderStatusName) {
        return getAll().stream()
                .filter(el -> el.getOrderStatusEnum().getOrderStatusDescription().equals(orderStatusName))
                .findFirst().orElse(null);
    }
}