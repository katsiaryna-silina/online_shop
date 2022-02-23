package by.epam.silina.online_shop.controller;

import by.epam.silina.online_shop.config.MenuIdentifier;
import by.epam.silina.online_shop.model.RoleEnum;
import by.epam.silina.online_shop.service.UserService;
import by.epam.silina.online_shop.service.impl.UserServiceImpl;
import by.epam.silina.online_shop.view.AdminMainMenuEnum;
import by.epam.silina.online_shop.view.ClientMainMenuEnum;
import by.epam.silina.online_shop.view.InitialMenuEnum;
import by.epam.silina.online_shop.view.View;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static by.epam.silina.online_shop.view.constant.MenuConstant.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private static final MainController instance = new MainController();
    private final UserService userService = UserServiceImpl.getInstance();
    private final View view = View.getInstance();

    public static MainController getInstance() {
        return instance;
    }

    public Class<? extends MenuIdentifier> handleRequest(MenuIdentifier selectedMenuItem) {
        if (selectedMenuItem instanceof InitialMenuEnum) {
            switch (selectedMenuItem.getCommand()) {
                case REGISTER_NEW_USER:
                    userService.register();
                    break;
                case DO_LOGIN:
                    var role = userService.login();
                    if (RoleEnum.ADMIN.equals(role.getRoleEnum())) {
                        return AdminMainMenuEnum.class;
                    } else if (RoleEnum.CLIENT.equals(role.getRoleEnum())) {
                        return ClientMainMenuEnum.class;
                    }
                    break;
                case DO_EXIT:
                    view.exitFromApp();
                    break;
                default:
                    view.showWarringMessage();
                    break;
            }
            return InitialMenuEnum.class;
        }
        //todo else sections
        return null;
    }
}
