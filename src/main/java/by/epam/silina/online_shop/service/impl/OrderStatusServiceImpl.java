package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.OrderStatusDAO;
import by.epam.silina.online_shop.dao.impl.OrderStatusDAOImpl;
import by.epam.silina.online_shop.model.OrderStatus;
import by.epam.silina.online_shop.service.OrderStatusService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for order status logic responsible for processing order status data from dao layer.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStatusServiceImpl implements OrderStatusService {
    private static final OrderStatusService instance = new OrderStatusServiceImpl();
    private final OrderStatusDAO orderStatusDAO = OrderStatusDAOImpl.getInstance();

    public static OrderStatusService getInstance() {
        return instance;
    }

    /**
     * Depending on order status name, requests data of order status with this name from order status dao
     * and returns this order status object.
     *
     * @param orderStatusName order status name
     * @return OrderStatus
     */
    @Override
    public OrderStatus getOrderStatusByName(String orderStatusName) {
        return orderStatusDAO.getOrderStatusByName(orderStatusName);
    }
}
