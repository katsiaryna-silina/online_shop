package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Feedback implements DAOIdentifier {
    private Long id;
    private String text;
    private LocalDate date;
    private boolean isActive;
    private User user;
}
