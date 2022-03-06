package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Data;

@Data
public class UserDetails implements DAOIdentifier {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private String telephoneNumber;
}
