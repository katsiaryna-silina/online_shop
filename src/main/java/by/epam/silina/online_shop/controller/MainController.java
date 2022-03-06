package by.epam.silina.online_shop.controller;

import by.epam.silina.online_shop.config.MenuIdentifier;
import by.epam.silina.online_shop.config.Session;
import by.epam.silina.online_shop.model.RoleEnum;
import by.epam.silina.online_shop.service.FeedbackService;
import by.epam.silina.online_shop.service.ItemService;
import by.epam.silina.online_shop.service.OrderService;
import by.epam.silina.online_shop.service.UserService;
import by.epam.silina.online_shop.service.impl.FeedbackServiceImpl;
import by.epam.silina.online_shop.service.impl.ItemServiceImpl;
import by.epam.silina.online_shop.service.impl.OrderServiceImpl;
import by.epam.silina.online_shop.service.impl.UserServiceImpl;
import by.epam.silina.online_shop.view.*;
import by.epam.silina.online_shop.view.menu.AdminMainMenuEnum;
import by.epam.silina.online_shop.view.menu.AdminOrderMenuEnum;
import by.epam.silina.online_shop.view.menu.ClientMainMenuEnum;
import by.epam.silina.online_shop.view.menu.InitialMenuEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

import static by.epam.silina.online_shop.config.MenuConstant.*;

/**
 * Main controller responsible for processing user's request and redirects to view or service layers.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MainController {
    private static final MainController instance = new MainController();
    private final UserService userService = UserServiceImpl.getInstance();
    private final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
    private final RegistrationView registrationView = RegistrationView.getInstance();
    private final LoginView loginView = LoginView.getInstance();
    private final ExitView exitView = ExitView.getInstance();
    private final UserView userView = UserView.getInstance();
    private final FeedbackView feedbackView = FeedbackView.getInstance();
    private final OrderView orderView = OrderView.getInstance();
    private final OrderService orderService = OrderServiceImpl.getInstance();
    private final ItemView itemView = ItemView.getInstance();
    private final ItemService itemService = ItemServiceImpl.getInstance();

    private final View view = View.getInstance();

    public static MainController getInstance() {
        return instance;
    }

    /**
     * Depending on the value passed, returns a menu with choices that is displayed to the user.
     *
     * @param selectedMenuItem selected menu item
     * @return MenuIdentifier
     */
    public Class<? extends MenuIdentifier> handleRequest(MenuIdentifier selectedMenuItem) {
        var command = selectedMenuItem.getCommand();
        if (selectedMenuItem instanceof InitialMenuEnum) {
            return processInitialMenuCommand(command);
        } else if (selectedMenuItem instanceof AdminMainMenuEnum) {
            return processAdminMenuCommand(command);
        } else if (selectedMenuItem instanceof AdminOrderMenuEnum) {
            return processAdminOrderMenuCommand(command);
        } else if (selectedMenuItem instanceof ClientMainMenuEnum) {
            return processClientMainMenuCommand(command);
        }
        return null;
    }

    private Class<? extends MenuIdentifier> processInitialMenuCommand(String command) {
        switch (command) {
            case REGISTER_NEW_USER:
                var userCount = userService.getUserCount();
                registrationView.showNumberOfUsers(userCount);
                boolean isNewUserRegistered = false;
                while (!isNewUserRegistered) {
                    var paramsForRegistration = registrationView.getParamsForRegistration();
                    if (userService.registerUser(paramsForRegistration)) {
                        isNewUserRegistered = true;
                    } else {
                        userView.showErrorRegistrationMessage();
                    }
                }
                break;
            case DO_LOGIN:
                while (Session.user == null) {
                    var paramsForLogin = loginView.getParamsForLogin();
                    Session.user = userService.login(paramsForLogin);
                    if (Session.user == null) {
                        loginView.showErrorMessage();
                    }
                }
                if (RoleEnum.ADMIN_ROLE.equals(Session.user.getRole().getRoleEnum())) {
                    return AdminMainMenuEnum.class;
                } else if (RoleEnum.CLIENT_ROLE.equals(Session.user.getRole().getRoleEnum())) {
                    return ClientMainMenuEnum.class;
                }
                break;
            case DO_EXIT:
                exitView.exitFromApp();
                break;
            default:
                view.showWarningMessage();
                break;
        }
        return InitialMenuEnum.class;
    }

    private Class<? extends MenuIdentifier> processAdminMenuCommand(String command) {
        switch (command) {
            case SHOW_ADMIN_INFO:
                var userInfo = userService.getUserInfo(Session.user);
                userView.showUserInfo(userInfo);
                break;
            case GO_TO_ORDERS_MENU:
                return AdminOrderMenuEnum.class;
            case SHOW_ALL_USERS:
                var allUsers = userService.getAllUsers();
                userView.showAllUsers(allUsers);
                break;
            case SHOW_ALL_FEEDBACKS:
                var allFeedbacks = feedbackService.getAllFeedbacks();
                feedbackView.showFeedbacks(allFeedbacks);
                break;
            case SHOW_FEEDBACKS_FROM_FILE:
                Map<Long, String> userIdToFeedbackTextMap = feedbackService.getFeedbackDataUserIdToFeedbackTextMap();
                feedbackView.showFeedbacksData(userIdToFeedbackTextMap);
                break;
            case DO_LOGOUT:
                Session.user = null;
                return InitialMenuEnum.class;
            default:
                view.showWarningMessage();
                break;
        }
        return AdminMainMenuEnum.class;
    }

    private Class<? extends MenuIdentifier> processAdminOrderMenuCommand(String command) {
        switch (command) {
            case SHOW_ALL_ORDERS:
                var allOrders = orderService.getAllOrders();
                orderView.showOrders(allOrders);
                break;
            case GO_BACK_TO_PREVIOUS_MENU:
                return AdminMainMenuEnum.class;
            case DO_LOGOUT:
                Session.user = null;
                return InitialMenuEnum.class;
            default:
                view.showWarningMessage();
                break;
        }
        return AdminOrderMenuEnum.class;
    }

    private Class<? extends MenuIdentifier> processClientMainMenuCommand(String command) {
        switch (command) {
            case SHOW_CLIENT_INFO:
                var userInfo = userService.getUserInfo(Session.user);
                userView.showUserInfo(userInfo);
                break;
            case SHOW_MY_ORDERS:
                var userOrders = orderService.getOrdersByUserId(Session.user.getId());
                orderView.showOrders(userOrders);
                break;
            case SHOW_ALL_ITEMS_SORTED_BY_DEFAULT:
                var allItemsSortedByDefault = itemService.getAllItemsSortedByDefault();
                itemView.showAllItems(allItemsSortedByDefault);
                break;
            case SHOW_ALL_ITEMS_SORTED_BY_PRICE_LOW_TO_HIGH:
                var allItemsSortedByPriceLowToHigh = itemService.getAllItemsSortedByPriceLowToHigh();
                itemView.showAllItems(allItemsSortedByPriceLowToHigh);
                break;
            case SHOW_ALL_ITEMS_SORTED_BY_PRICE_HIGH_TO_LOW:
                var allItemsSortedByPriceHighToLow = itemService.getAllItemsSortedByPriceHighToLow();
                itemView.showAllItems(allItemsSortedByPriceHighToLow);
                break;
            case CREATE_NEW_ORDER:
                var allItems = itemService.getAllItems();
                itemView.showAllItems(allItems);
                Map<Long, Integer> itemIdToNumberOfItemsMap = itemView.getItemIdToNumberOfItemsMap();
                boolean isOrderCreated = orderService.createOrderWithItems(itemIdToNumberOfItemsMap);
                if (isOrderCreated) {
                    orderView.showOrderSuccessCreationMessage();
                } else {
                    orderView.showOrderErrorCreationMessage();
                }
                break;
            case SHOW_MY_FEEDBACKS:
                var userFeedbacks = feedbackService.getFeedbacksByUserId(Session.user.getId());
                feedbackView.showFeedbacks(userFeedbacks);
                break;
            case LEAVE_FEEDBACK_ON_ONLINE_SHOP:
                var feedbackText = feedbackView.getFeedbackText();
                feedbackService.createFeedback(feedbackText);
                feedbackView.showFeedbackCreationMessage();
                break;
            case DELETE_FEEDBACK_ON_ONLINE_SHOP:
                feedbackView.showDeletionFeedbackMessage();
                var feedbackId = feedbackView.getFeedbackIdFromConsole();
                boolean isFeedbackDeleted = feedbackService.deleteFeedbackById(feedbackId);
                if (isFeedbackDeleted) {
                    feedbackView.showFeedbackSuccessDeletionMessage();
                } else {
                    feedbackView.showFeedbackErrorDeletionMessage();
                }
                break;
            case DO_LOGOUT:
                Session.user = null;
                return InitialMenuEnum.class;
            default:
                view.showWarningMessage();
                break;
        }
        return ClientMainMenuEnum.class;
    }
}
