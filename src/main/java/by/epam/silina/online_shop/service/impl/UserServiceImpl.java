package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.UserDAO;
import by.epam.silina.online_shop.dao.impl.UserDAOImpl;
import by.epam.silina.online_shop.exception.UserServiceException;
import by.epam.silina.online_shop.model.Role;
import by.epam.silina.online_shop.model.RoleEnum;
import by.epam.silina.online_shop.model.User;
import by.epam.silina.online_shop.service.UserService;
import by.epam.silina.online_shop.util.ConsoleReader;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
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
        var user = User.builder()
                .uniqueNumber(UUID.randomUUID())
                //todo add role
                .role(Role.builder().roleEnum(RoleEnum.ADMIN).build())
                .email(getEmailFromConsoleWithValidation())
                .username(getUsernameFromConsoleWithValidation())
                .password(getPasswordFromConsoleWithValidation())
                .build();
        userDAO.save(user);
        log.info("User with id={} has been registered.", user.getId());
    }

    @Override
    public Role login() {
        var username = getUserNameFromConsole();
        var user = userDAO.getUserByUsername(username);
        if (user == null) {
            log.error("User with username={} doesn't exist", username);
            throw new UserServiceException("User with username=" + username + " doesn't exist");
        } else {
            checkUserPassword(user);
            return user.getRole();
        }
    }

    public String getEmailFromConsoleWithValidation() {
        System.out.println("Write email:");

        String email = scanner.nextLine();
        if (userDAO.isUserWithEmailPresent(email)) {
            System.out.println("This email has been already registered.");
            getEmailFromConsoleWithValidation();
        } else if (!isEmailValid(email)) {
            System.out.println("Email should be valid.");
            getEmailFromConsoleWithValidation();
        }
        return email;
    }

    private boolean isEmailValid(String email) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
    }

    private String getUsernameFromConsoleWithValidation() {
        var username = getUserNameFromConsole();
        if (userDAO.isUserWithUsernamePresent(username)) {
            System.out.println("This username has been already registered.");
            getUsernameFromConsoleWithValidation();
        } else if (username.length() > 16) {
            System.out.println("Username should be less than 16 symbols.");
            getUsernameFromConsoleWithValidation();
        }
        return username;
    }

    private String getPasswordFromConsoleWithValidation() {
        var password = getPasswordFromConsole();
        if (password.length() < 8 || password.length() > 20) {
            System.out.println("Password length should be more than 8 and less than 20");
            getPasswordFromConsoleWithValidation();
        }
        return password;
    }

    private String getUserNameFromConsole() {
        System.out.println("Write username:");
        return scanner.nextLine();
    }

    private String getPasswordFromConsole() {
        System.out.println("Write password:");
        return scanner.nextLine();
    }

    private void checkUserPassword(User user) {
        var password = getPasswordFromConsole();
        if (user.getPassword().equals(password)) {
            System.out.println("Hello, " + user.getUsername() + "!");
        } else {
            System.out.println("Password is not correct.");
            checkUserPassword(user);
        }
    }
}
