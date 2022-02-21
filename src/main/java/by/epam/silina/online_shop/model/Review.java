package by.epam.silina.online_shop.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {
    private Long id;
    private String feedback;
    private LocalDate date;
    private boolean isActive;
    private User user;
}
