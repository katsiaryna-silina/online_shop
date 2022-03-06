package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.util.ConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginView {
    private static final LoginView instance = new LoginView();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();

    public static LoginView getInstance() {
        return instance;
    }

    public List<String> getParamsForLogin() {
        List<String> params = new ArrayList<>();
        params.add(getUsernameFromConsole());
        params.add(getPasswordFromConsole());
        return params;
    }

    private String getUsernameFromConsole() {
        System.out.println("Write username:");
        return scanner.nextLine();
    }

    private String getPasswordFromConsole() {
        System.out.println("Write password:");
        return scanner.nextLine();
    }

    public void showErrorMessage() {
        System.out.println("Username or password are not correct. Try again.");
    }
}
