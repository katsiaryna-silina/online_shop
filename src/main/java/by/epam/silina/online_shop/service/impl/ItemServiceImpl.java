package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.ItemDAO;
import by.epam.silina.online_shop.dao.impl.ItemDAOImpl;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import by.epam.silina.online_shop.model.Item;
import by.epam.silina.online_shop.service.ItemService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for feedbacks logic responsible for processing feedback data from dao layer.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemServiceImpl implements ItemService {
    private static final ItemService instance = new ItemServiceImpl();
    private final ItemDAO itemDAO = ItemDAOImpl.getInstance();

    public static ItemService getInstance() {
        return instance;
    }

    /**
     * Requests data of all items objects from item dao and returns list of them.
     *
     * @return List<Item>
     */
    @Override
    public List<Item> getAllItems() {
        return itemDAO.getAll();
    }

    /**
     * Requests data of all items objects from item dao,
     * sorts them by item name in alphabet order and returns list of them.
     *
     * @return List<Item>
     */
    @Override
    public List<Item> getAllItemsSortedByDefault() {
        var allItems = itemDAO.getAll();
        return allItems.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Requests data of all items objects from item dao,
     * sorts them by item price low to high and returns list of them.
     *
     * @return List<Item>
     */
    @Override
    public List<Item> getAllItemsSortedByPriceLowToHigh() {
        var allItems = itemDAO.getAll();
        return allItems.stream()
                .sorted(Comparator.comparing(Item::getPrice))
                .collect(Collectors.toList());
    }

    /**
     * Requests data of all items objects from item dao,
     * sorts them by item price high to low and returns list of them.
     *
     * @return List<Item>
     */
    @Override
    public List<Item> getAllItemsSortedByPriceHighToLow() {
        var allItems = itemDAO.getAll();
        Collections.sort(allItems, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getPrice().compareTo(o2.getPrice()) > 0) {
                    return -1;
                } else if (o1.getPrice().compareTo(o2.getPrice()) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return allItems;
    }

    /**
     * Depending on item id, requests data of item with this id from item dao and returns this item object.
     *
     * @param itemId item id
     * @return Item
     */
    @Override
    public Item getItemById(Long itemId) {
        Item item = null;
        try {
            Optional<Item> optionalItem = itemDAO.get(itemId);
            if (optionalItem.isPresent()) {
                item = optionalItem.get();
            }
        } catch (GenericDAOImplException e) {
            log.error("", e);
        }
        return item;
    }
}
