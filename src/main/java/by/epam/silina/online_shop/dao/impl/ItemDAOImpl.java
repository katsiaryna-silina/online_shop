package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.ItemDAO;
import by.epam.silina.online_shop.model.Item;

/**
 * Item dao implementation responsible for getting item's data from datasource.
 */
public class ItemDAOImpl extends GenericDAOImpl<Item> implements ItemDAO {
    private static final ItemDAO instance = new ItemDAOImpl();

    private ItemDAOImpl() {
        super(Item.class);
    }

    public static ItemDAO getInstance() {
        return instance;
    }
}
