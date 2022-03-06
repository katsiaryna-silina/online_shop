package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.config.Session;
import by.epam.silina.online_shop.dao.OrderDAO;
import by.epam.silina.online_shop.dao.impl.OrderDAOImpl;
import by.epam.silina.online_shop.exception.OrderDAOException;
import by.epam.silina.online_shop.model.Item;
import by.epam.silina.online_shop.model.Order;
import by.epam.silina.online_shop.service.ItemService;
import by.epam.silina.online_shop.service.OrderService;
import by.epam.silina.online_shop.service.OrderStatusService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static by.epam.silina.online_shop.config.OrderStatusConstant.WAITING_FOR_PAYMENT;

/**
 * Service for order logic responsible for processing order data from dao layer.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderServiceImpl implements OrderService {
    private static final OrderService instance = new OrderServiceImpl();
    private final OrderDAO orderDAO = OrderDAOImpl.getInstance();
    private final ItemService itemService = ItemServiceImpl.getInstance();
    private final OrderStatusService orderStatusService = OrderStatusServiceImpl.getInstance();

    public static OrderService getInstance() {
        return instance;
    }

    /**
     * Requests data of all order objects from order dao and returns list of them.
     *
     * @return List<Order>
     */
    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    /**
     * Depending on user id, requests data of user's orders from order dao and returns a list of them.
     *
     * @param userId user's id
     * @return List<Order>
     */
    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> userOrders = new ArrayList<>();
        try {
            userOrders = orderDAO.getOrdersByUserId(userId);
        } catch (OrderDAOException e) {
            log.error("", e);
        }
        return userOrders;
    }

    /**
     * Creates and saves order using data from passed map item id to number of items.
     * Returns boolean value whether order has been created and saved to datasource.
     *
     * @param itemIdToNumberOfItemsMap map item id to number of items
     * @return boolean
     */
    @Override
    public boolean createOrderWithItems(Map<Long, Integer> itemIdToNumberOfItemsMap) {
        BigDecimal finalPriceOfItems = getFinalPriceOfItems(itemIdToNumberOfItemsMap);
        if (finalPriceOfItems == null) {
            return false;
        } else {
            Order order = Order.builder()
                    .itemIdToNumberOfItems(itemIdToNumberOfItemsMap)
                    .finalPrice(finalPriceOfItems)
                    .orderStatus(orderStatusService.getOrderStatusByName(WAITING_FOR_PAYMENT))
                    .date(LocalDate.now())
                    .user(Session.user)
                    .build();
            orderDAO.save(order);
            return true;
        }
    }

    private BigDecimal getFinalPriceOfItems(Map<Long, Integer> itemIdToNumberOfItemsMap) {
        if (itemIdToNumberOfItemsMap.isEmpty()) {
            return null;
        } else {
            var finalOrderPrice = BigDecimal.ZERO;
            for (Map.Entry<Long, Integer> entry : itemIdToNumberOfItemsMap.entrySet()) {
                var itemId = entry.getKey();
                Item item = itemService.getItemById(itemId);
                BigDecimal itemPrice = item.getPrice();

                var numberOfItems = entry.getValue();

                var resultItemsPrice = itemPrice.multiply(BigDecimal.valueOf(numberOfItems));
                finalOrderPrice = finalOrderPrice.add(resultItemsPrice);
            }
            return finalOrderPrice;
        }
    }
}
