package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Item implements DAOIdentifier, Comparable<Item> {
    private Long id;
    private String name;
    private UUID uniqueNumber;
    private BigDecimal price;
    private String summary;

    @Override
    public int compareTo(Item otherItem) {
        return CharSequence.compare(getName(), otherItem.getName());
    }
}
