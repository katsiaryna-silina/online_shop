package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.model.MenuParamEnum;
import by.epam.silina.online_shop.service.MenuService;
import by.epam.silina.online_shop.util.ConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuServiceImpl implements MenuService {
    private static final MenuServiceImpl instance = new MenuServiceImpl();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();

    public static MenuServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void showMenu() {
        Arrays.stream(MenuParamEnum.values())
                .forEach(el -> System.out.println(el.getNumber() + ". " + el.getCommand()));
        System.out.println("Choose option:");
    }

    @Override
    public void exitFromMenu() {
        System.out.println("See you.");
        scanner.close();
        exit(0);
    }
}
