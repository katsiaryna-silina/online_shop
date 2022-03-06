package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.dao.ItemDAO;
import by.epam.silina.online_shop.dao.impl.ItemDAOImpl;
import by.epam.silina.online_shop.model.Item;
import by.epam.silina.online_shop.util.ItemUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Item util with logic for creation initial items.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemUtilImpl implements ItemUtil {
    private static final ItemUtilImpl instance = new ItemUtilImpl();
    private final ItemDAO itemDAO = ItemDAOImpl.getInstance();

    public static ItemUtilImpl getInstance() {
        return instance;
    }

    /**
     * Creates and adds items to datasource.
     */
    public void addInitialItemsToDataSource() {
        List<Item> initialItems = createInitialItems();
        initialItems.forEach(itemDAO::save);
    }

    private List<Item> createInitialItems() {
        List<Item> items = new ArrayList<>();
        var bookOne = Item.builder()
                .name("Book of peace")
                .price(BigDecimal.valueOf(100.5))
                .summary("The Book of Peace is an ancient book noted for being a priceless treasure that brought peace and harmony to the Twelve Cities of mankind.")
                .uniqueNumber(UUID.randomUUID())
                .build();
        items.add(bookOne);
        var bookTwo = Item.builder()
                .name("Book of war")
                .price(BigDecimal.valueOf(120.0))
                .summary("The Book of War is an ancient book about all world wars.")
                .uniqueNumber(UUID.randomUUID())
                .build();
        items.add(bookTwo);
        var pencil = Item.builder()
                .name("Pencil")
                .price(BigDecimal.valueOf(2.0))
                .summary("An instrument for writing or drawing.")
                .uniqueNumber(UUID.randomUUID())
                .build();
        items.add(pencil);
        var pen = Item.builder()
                .name("Pen")
                .price(BigDecimal.valueOf(2.5))
                .summary("An instrument for writing or drawing.")
                .uniqueNumber(UUID.randomUUID())
                .build();
        items.add(pen);
        return items;
    }
}
