package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.dao.impl.UserDAOImpl;
import by.epam.silina.online_shop.service.UserService;
import by.epam.silina.online_shop.util.ConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();
    private final UserDAO userDAO = UserDAOImpl.getInstance();
    private final Scanner scanner = ConsoleReader.getInstance().getScanner();

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void showAllUsers() {
        var map = userDAO.getAll();
        if (map.size() == 0) {
            System.out.println("There are no registered users");
        } else {
            map.values().forEach(System.out::println);
        }
    }

    @Override
    public void register() {
        User user = User.builder()
                .uniqueNumber(UUID.randomUUID())
/*              todo add role
                .role(Role.builder().build())
*/
                .email(getEmailFromConsole())
                .username(getUsernameFromConsole())
                .password(getPasswordFromConsole())
                .build();
        userDAO.save(user);
    }

    public String getEmailFromConsole() {
        System.out.println("Write email:");

        String email = scanner.nextLine();
        if (userDAO.isUserWithEmailPresent(email)) {
            System.out.println("This email has been already registered.");
            getEmailFromConsole();
        } else if (!isEmailValid(email)) {
            System.out.println("Email should be valid.");
            getEmailFromConsole();
        }
        return email;
    }

    private boolean isEmailValid(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    private String getUsernameFromConsole() {
        System.out.println("Write username:");

        String username = scanner.nextLine();
        if (userDAO.isUserWithUsernamePresent(username)) {
            System.out.println("This username has been already registered.");
            getUsernameFromConsole();
        } else if (username.length() > 16) {
            System.out.println("Username should be less than 16 symbols.");
            getUsernameFromConsole();
        }
        return username;
    }

    private String getPasswordFromConsole() {
        System.out.println("Write password:");

        String password = scanner.nextLine();
        if (password.length() < 8 || password.length() > 20) {
            System.out.println("Password length should be more than 8 and less than 20");
            getPasswordFromConsole();
        }
        return password;
    }
}
