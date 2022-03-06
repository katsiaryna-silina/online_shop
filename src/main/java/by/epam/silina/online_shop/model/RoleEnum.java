package by.epam.silina.online_shop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static by.epam.silina.online_shop.config.RoleConstant.ADMIN;
import static by.epam.silina.online_shop.config.RoleConstant.CLIENT;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN_ROLE(ADMIN),
    CLIENT_ROLE(CLIENT);

    private final String roleDescription;
}
