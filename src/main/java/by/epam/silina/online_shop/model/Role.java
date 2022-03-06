package by.epam.silina.online_shop.model;

import by.epam.silina.online_shop.config.DAOIdentifier;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Role implements DAOIdentifier, Serializable {
    private Long id;
    private RoleEnum roleEnum;
}
