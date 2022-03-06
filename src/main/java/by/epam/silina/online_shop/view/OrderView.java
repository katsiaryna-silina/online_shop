package by.epam.silina.online_shop.view;

import by.epam.silina.online_shop.model.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderView {
    private static final OrderView instance = new OrderView();

    public static OrderView getInstance() {
        return instance;
    }

    public void showOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("There are no any orders.");
        } else {
            orders.forEach(System.out::println);
        }
    }

    public void showOrderSuccessCreationMessage() {
        System.out.println("Your order has created!");
    }

    public void showOrderErrorCreationMessage() {
        System.out.println("Order is not created. If you want try to create it later.");
    }
}
