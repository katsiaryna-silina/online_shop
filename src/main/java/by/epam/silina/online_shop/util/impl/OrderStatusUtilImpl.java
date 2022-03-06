package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.dao.OrderStatusDAO;
import by.epam.silina.online_shop.dao.impl.OrderStatusDAOImpl;
import by.epam.silina.online_shop.model.OrderStatus;
import by.epam.silina.online_shop.model.OrderStatusEnum;
import by.epam.silina.online_shop.util.OrderStatusUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Order status util with logic for creation initial order statuses.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStatusUtilImpl implements OrderStatusUtil {
    private static final OrderStatusUtil instance = new OrderStatusUtilImpl();

    private final OrderStatusDAO orderStatusDAO = OrderStatusDAOImpl.getInstance();

    public static OrderStatusUtil getInstance() {
        return instance;
    }

    /**
     * Creates and adds order statuses to datasource.
     */
    public void addInitialOrderStatusesToDataSource() {
        var initialOrderStatuses = createInitialOrderStatuses();
        initialOrderStatuses.forEach(orderStatusDAO::save);
    }

    private List<OrderStatus> createInitialOrderStatuses() {
        return Arrays.stream(OrderStatusEnum.values())
                .map(el -> OrderStatus.builder()
                        .orderStatusEnum(el)
                        .build())
                .collect(Collectors.toList());
    }
}
