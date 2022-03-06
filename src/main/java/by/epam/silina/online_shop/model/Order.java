package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Data
@Builder
public class Order implements DAOIdentifier {
    private Long id;
    private Map<Long, Integer> itemIdToNumberOfItems;
    private BigDecimal finalPrice;
    private LocalDate date;
    private OrderStatus orderStatus;
    private User user;
}
