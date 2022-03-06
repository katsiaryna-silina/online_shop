package by.epam.silina.online_shop.datasource;

import by.epam.silina.online_shop.config.DAOIdentifier;
import by.epam.silina.online_shop.model.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Datasource for all entities stores data in memory.
 * Provides data depending on the type of entity.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryDataSource {
    private static final InMemoryDataSource instance = new InMemoryDataSource();
    private final List<User> users = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<OrderStatus> orderStatuses = new ArrayList<>();
    private final List<UserDetails> userDetails = new ArrayList<>();
    private final List<Role> roles = new ArrayList<>();


    public static InMemoryDataSource getInstance() {
        return instance;
    }

    /**
     * Depending on the entity class passed that extends DAOIdentifier, returns List of entity object  stored in memory.
     *
     * @param clazz Class<T> where T extends DAOIdentifier
     * @return <T extends DAOIdentifier> List<T> list of entity objects
     */
    public <T extends DAOIdentifier> List<T> getEntityList(Class<T> clazz) {
        if (User.class.equals(clazz)) {
            return (List<T>) users;
        } else if (Item.class.equals(clazz)) {
            return (List<T>) items;
        } else if (Feedback.class.equals(clazz)) {
            return (List<T>) feedbacks;
        } else if (Order.class.equals(clazz)) {
            return (List<T>) orders;
        } else if (OrderStatus.class.equals(clazz)) {
            return (List<T>) orderStatuses;
        } else if (UserDetails.class.equals(clazz)) {
            return (List<T>) userDetails;
        } else if (Role.class.equals(clazz)) {
            return (List<T>) roles;
        } else {
            return null;
        }
    }
}
