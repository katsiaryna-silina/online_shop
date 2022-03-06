package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.OrderDAO;
import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import by.epam.silina.online_shop.exception.OrderDAOException;
import by.epam.silina.online_shop.model.Order;
import by.epam.silina.online_shop.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Order dao implementation responsible for getting order's data from datasource.
 */
public class OrderDAOImpl extends GenericDAOImpl<Order> implements OrderDAO {
    private static final OrderDAO instance = new OrderDAOImpl();
    private final UserDAO userDAO = UserDAOImpl.getInstance();

    private OrderDAOImpl() {
        super(Order.class);
    }

    public static OrderDAO getInstance() {
        return instance;
    }

    /**
     * Depending on user id, requests data of user's orders and returns a list of them.
     *
     * @param userId user's id
     * @return List<Order>
     */
    @Override
    public List<Order> getOrdersByUserId(Long userId) throws OrderDAOException {
        List<Order> userOrders;
        Optional<User> user;
        try {
            user = userDAO.get(userId);
            if (user.isPresent()) {
                var allOrders = getAll();
                userOrders = allOrders.stream()
                        .filter(el -> el.getUser().getId().equals(userId))
                        .collect(Collectors.toList());
                return userOrders;
            } else {
                throw new OrderDAOException("User with id " + userId + " doesn't exist.");
            }
        } catch (GenericDAOImplException e) {
            throw new OrderDAOException("User id can not be null", e);
        }
    }
}
