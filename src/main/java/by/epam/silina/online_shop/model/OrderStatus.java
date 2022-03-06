package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderStatus implements DAOIdentifier {
    private Long id;
    private OrderStatusEnum orderStatusEnum;
}
