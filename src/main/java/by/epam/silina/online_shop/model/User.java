package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@ToString(exclude = "password")
public class User implements DAOIdentifier, Serializable {
    private Long id;
    private UUID uniqueNumber;
    private String username;
    private String email;
    private String password;
    private transient UserDetails userDetails;
    private Role role;
}
