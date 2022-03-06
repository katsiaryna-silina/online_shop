package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.util.ConsoleReader;
import by.epam.silina.online_shop.util.ValidationUtil;
import by.epam.silina.online_shop.util.impl.ValidationUtilImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static by.epam.silina.online_shop.config.UserConstant.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegistrationView {
    private static final RegistrationView instance = new RegistrationView();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();
    private final ValidationUtil validationUtil = ValidationUtilImpl.getInstance();

    public static RegistrationView getInstance() {
        return instance;
    }

    public void showNumberOfUsers(Long userEntityCount) {
        System.out.println("There are " + userEntityCount + " registered users. Just know this info.");
    }

    public List<String> getParamsForRegistration() {
        List<String> params = new ArrayList<>();
        params.add(getEmailFromConsole());
        params.add(getUsernameFromConsole());
        params.add(getPasswordFromConsole());
        return params;
    }

    private String getEmailFromConsole() {
        System.out.println("Write email:");
        var email = scanner.nextLine();
        if (!validationUtil.isEmailValid(email)) {
            System.out.println("Email should be valid.");
            return getEmailFromConsole();
        }
        return email;
    }

    private String getUsernameFromConsole() {
        System.out.println("Write username:");
        var username = scanner.nextLine();
        if (!validationUtil.isUsernameValid(username)) {
            System.out.println("Username should be more than " + USERNAME_MIN_LENGTH + " and  less than " + USERNAME_MAX_LENGTH + " symbols.");
            return getUsernameFromConsole();
        }
        return username;
    }

    private String getPasswordFromConsole() {
        System.out.println("Write password:");
        var password = scanner.nextLine();
        if (!validationUtil.isPasswordValid(password)) {
            System.out.println("Password should be more than " + PASSWORD_MIN_LENGTH + " and less than " + PASSWORD_MAX_LENGTH);
            return getPasswordFromConsole();
        }
        return password;
    }
}
