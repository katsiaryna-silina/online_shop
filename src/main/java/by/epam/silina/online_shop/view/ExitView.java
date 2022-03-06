package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.util.ConsoleReader;
import by.epam.silina.online_shop.util.EndAppUtil;
import by.epam.silina.online_shop.util.impl.EndAppUtilImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExitView {
    private static final ExitView instance = new ExitView();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();
    private final EndAppUtil endAppUtil = EndAppUtilImpl.getInstance();

    public static ExitView getInstance() {
        return instance;
    }

    public void exitFromApp() {
        System.out.println("See you.");
        scanner.close();
        endAppUtil.endApp();
    }
}
