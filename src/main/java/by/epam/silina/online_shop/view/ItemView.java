package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.model.Item;
import by.epam.silina.online_shop.util.ConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemView {
    private static final ItemView instance = new ItemView();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();

    public static ItemView getInstance() {
        return instance;
    }

    public void showAllItems(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("There are no items.");
        } else {
            items.forEach(System.out::println);
        }
    }

    public Map<Long, Integer> getItemIdToNumberOfItemsMap() {
        Map<Long, Integer> itemToNumberOfItems = new HashMap<>();
        System.out.println("Do you want to add item to order? Write \"yes\" or \"no\" :");
        var userAnswer = scanner.nextLine();
        while (userAnswer.equals("yes")) {
            itemToNumberOfItems.put(getItemIdFromConsole(), getNumberOfItemFromConsole());
            System.out.println("Do you want to add item to order? Write \"yes\" or \"no\" :");
            userAnswer = scanner.nextLine();
        }
        if (userAnswer.equals("no")) {
            return itemToNumberOfItems;
        } else {
            System.out.println("Your answer is incorrect. Try again.");
            return getItemIdToNumberOfItemsMap();
        }
    }

    private Long getItemIdFromConsole() {
        Integer itemId = null;
        boolean isItemIdEntered = false;
        while (!isItemIdEntered) {
            System.out.println("Choose item and write only number of item id (For example: 5) :");
            String enteredItemId = scanner.nextLine();
            try {
                itemId = Integer.parseInt(enteredItemId);
                isItemIdEntered = true;
            } catch (Exception e) {
                System.out.println("Incorrect id entered. Try again.");
            }
        }
        return Long.valueOf(itemId);
    }

    private Integer getNumberOfItemFromConsole() {
        Integer number = null;
        boolean isCorrectNumberEntered = false;
        while (!isCorrectNumberEntered) {
            System.out.println("Write total number of item to order. (If you have already added this item, number of it will change to this value) :");
            String enteredNumber = scanner.nextLine();
            try {
                number = Integer.parseInt(enteredNumber);
                isCorrectNumberEntered = true;
            } catch (Exception e) {
                System.out.println("Incorrect number entered. Try again.");
            }
        }
        return number;
    }
}
