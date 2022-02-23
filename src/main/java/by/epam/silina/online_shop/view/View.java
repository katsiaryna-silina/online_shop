package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.config.MenuIdentifier;
import by.epam.silina.online_shop.controller.MainController;
import by.epam.silina.online_shop.util.ConsoleReader;
import by.epam.silina.online_shop.util.EndAppUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class View {
    private static View instance;
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();
    private final MainController mainController = MainController.getInstance();
    private final EndAppUtil endAppUtil = EndAppUtil.getInstance();


    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

    public void doAction() {
        Class<? extends MenuIdentifier> currentMenuEnum = InitialMenuEnum.class;
        showMenu(currentMenuEnum);
        while (scanner.hasNext()) {
            var request = scanner.nextLine();
            currentMenuEnum = parseAndSendRequest(currentMenuEnum, request);
            showMenu(currentMenuEnum);
        }
        exitFromApp();
    }

    public <T extends MenuIdentifier> void showMenu(Class<T> menuParamEnum) {
        AtomicInteger i = new AtomicInteger(0);
        Arrays.stream(menuParamEnum.getEnumConstants())
                .forEach(el -> System.out.println(i.incrementAndGet() + ". " + el.getCommand()));
        System.out.println("Choose option:");
    }

    private Class<? extends MenuIdentifier> parseAndSendRequest(Class<? extends MenuIdentifier> menuParamEnum, String request) {
        try {
            var requestOption = Integer.parseInt(request);
            MenuIdentifier[] menuEnumConstants = menuParamEnum.getEnumConstants();
            MenuIdentifier menuEnumConstant = menuEnumConstants[requestOption - 1];
            return mainController.handleRequest(menuEnumConstant);
        } catch (Exception e) {
            showWarringMessage();
            //todo return?
            return menuParamEnum;
        }
    }

    public void exitFromApp() {
        System.out.println("See you.");
        scanner.close();
        endAppUtil.endApp();
    }

    public void showWarringMessage() {
        System.out.println("Please, choose right option and type only its number.");
    }
}
