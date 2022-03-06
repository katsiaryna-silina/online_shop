package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.ItemDAO;
import by.epam.silina.online_shop.dao.impl.ItemDAOImpl;
import by.epam.silina.online_shop.model.Item;
import by.epam.silina.online_shop.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ItemServiceImplTest {
    private static ItemService itemService;
    private static ItemDAO itemDAO;

    @BeforeAll
    static void setup() {
        itemDAO = Mockito.mock(ItemDAO.class);
        try (MockedStatic<ItemDAOImpl> mockedItemDAO = Mockito.mockStatic(ItemDAOImpl.class)) {
            mockedItemDAO.when(ItemDAOImpl::getInstance).thenReturn(itemDAO);
            itemService = ItemServiceImpl.getInstance();
        }
    }

    @Test
    void getAllItems() {
        List<Item> expectedItems = new ArrayList<>();

        Mockito.when(itemDAO.getAll()).thenReturn(expectedItems);
        List<Item> actualItems = itemService.getAllItems();
        Assertions.assertEquals(expectedItems, actualItems);
        Mockito.verify(itemDAO, Mockito.times(1)).getAll();
    }
}