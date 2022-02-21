package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.controller.MenuController;
import by.epam.silina.online_shop.service.AppService;
import by.epam.silina.online_shop.service.MenuService;
import by.epam.silina.online_shop.util.ConsoleReader;

import java.util.Scanner;

public class AppServiceImpl implements AppService {
    private static final AppServiceImpl instance = new AppServiceImpl();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();
    private final MenuController menuController = MenuController.getInstance();
    private final MenuService menuService = MenuServiceImpl.getInstance();

    private AppServiceImpl() {
    }

    public static AppServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void init() {
        try {
            menuService.showMenu();
            while (scanner.hasNext()) {
                var request = scanner.nextLine();
                parseAndSendRequest(request);
                menuService.showMenu();
            }
        } finally {
            menuService.exitFromMenu();
        }
    }

    private void parseAndSendRequest(String request) {
        try {
            var requestOption = Integer.parseInt(request);
            menuController.handleRequest(requestOption);
        } catch (Exception e) {
            System.out.println("ConsoleReaderException. Please, choose right option and type only its number.");
        }
    }
}
