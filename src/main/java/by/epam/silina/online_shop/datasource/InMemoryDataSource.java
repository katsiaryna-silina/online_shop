package by.epam.silina.online_shop.datasource;

import by.epam.silina.online_shop.dao.DAOIdentifier;
import by.epam.silina.online_shop.model.Item;
import by.epam.silina.online_shop.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InMemoryDataSource {
    private static final InMemoryDataSource instance = new InMemoryDataSource();
    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Item> items = new HashMap<>();

    public static InMemoryDataSource getInstance() {
        return instance;
    }

    public <T extends DAOIdentifier> Map<Long, T> getMap(Class<T> clazz) {
        if (User.class.equals(clazz)) {
            return (Map<Long, T>) users;
        } else if (Item.class.equals(clazz)) {
            return (Map<Long, T>) items;
        } else {
            return null;
        }
    }
}
