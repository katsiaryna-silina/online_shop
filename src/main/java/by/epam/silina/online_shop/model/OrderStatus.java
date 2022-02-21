package by.epam.silina.online_shop.model;

import lombok.Data;

@Data
public class OrderStatus {
    private Long id;
    private OrderStatusEnum orderStatusEnum;
    private String description;
}
