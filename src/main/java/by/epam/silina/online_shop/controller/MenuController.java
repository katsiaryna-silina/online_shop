package by.epam.silina.online_shop.controller;

import by.epam.silina.online_shop.service.MenuService;
import by.epam.silina.online_shop.service.UserService;
import by.epam.silina.online_shop.service.impl.MenuServiceImpl;
import by.epam.silina.online_shop.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static by.epam.silina.online_shop.constant.MenuConstant.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuController {
    private static final MenuController instance = new MenuController();
    private final UserService userService = UserServiceImpl.getInstance();
    private final MenuService menuService = MenuServiceImpl.getInstance();

    public static MenuController getInstance() {
        return instance;
    }

    public void handleRequest(int requestOption) {
        switch (requestOption) {
            case ONE:
                userService.register();
                break;
            case THREE:
                userService.showAllUsers();
                break;
            case FOUR:
                menuService.exitFromMenu();
                break;
            default:
                System.out.println("Please, choose right option and type only its number.");
                break;
        }
    }
}
