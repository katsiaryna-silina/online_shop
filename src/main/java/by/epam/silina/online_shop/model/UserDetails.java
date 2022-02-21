package by.epam.silina.online_shop.model;

import lombok.Data;

@Data
public class UserDetails {
    private Long userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String telephoneNumber;
}
