package by.epam.silina.online_shop.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Feedback {
    private Long id;
    private String text;
    private LocalDate date;
    private boolean isActive;
    private User user;
}
