package by.epam.silina.online_shop.service;

import by.epam.silina.online_shop.model.Item;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();

    List<Item> getAllItemsSortedByDefault();

    List<Item> getAllItemsSortedByPriceLowToHigh();

    List<Item> getAllItemsSortedByPriceHighToLow();

    Item getItemById(Long itemId);
}
