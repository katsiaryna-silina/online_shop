package by.epam.silina.online_shop.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Order {
    private Long id;
    private String orderNumber;
    private Item item;
    private Integer countOfItems;
    private BigDecimal finalPrice;
    private LocalDate date;
    private OrderStatus orderStatus;
    private User user;
}
