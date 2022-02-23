package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Item implements DAOIdentifier {
    private Long id;
    private String name;
    private UUID uniqueNumber;
    private BigDecimal price;
    private String summary;
}
