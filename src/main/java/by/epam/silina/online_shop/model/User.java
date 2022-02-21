package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.dao.DAOIdentifier;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class User implements DAOIdentifier {
    private Long id;
    private UUID uniqueNumber;
    private String username;
    private String email;
    private String password;
    private UserDetails userDetails;
    private Role role;
}
