package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserView {
    private static final UserView instance = new UserView();

    public static UserView getInstance() {
        return instance;
    }

    public void showUserInfo(String userInfo) {
        System.out.println(userInfo);
    }

    public void showAllUsers(List<User> users) {
        if (users.isEmpty()) {
            System.out.println("There are no registered users");
        } else {
            users.forEach(System.out::println);
        }
    }

    public void showErrorRegistrationMessage() {
        System.out.println("This email or username has been already registered.");
    }
}
