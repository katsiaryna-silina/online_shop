package by.epam.silina.online_shop.util.impl;

import by.epam.silina.online_shop.dao.RoleDAO;
import by.epam.silina.online_shop.dao.impl.RoleDAOImpl;
import by.epam.silina.online_shop.model.Role;
import by.epam.silina.online_shop.model.RoleEnum;
import by.epam.silina.online_shop.util.RoleUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Role util with logic for creation initial roles.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleUtilImpl implements RoleUtil {
    private static final RoleUtil instance = new RoleUtilImpl();
    private final RoleDAO roleDAO = RoleDAOImpl.getInstance();

    public static RoleUtil getInstance() {
        return instance;
    }

    /**
     * Creates and adds roles to datasource.
     */
    public void addInitialRolesToDataSource() {
        var initialRoles = createInitialRoles();
        initialRoles.forEach(roleDAO::save);
    }

    private List<Role> createInitialRoles() {
        List<Role> roles = new ArrayList<>();
        var adminRole = Role.builder()
                .roleEnum(RoleEnum.ADMIN_ROLE)
                .build();
        roles.add(adminRole);
        var clientRole = Role.builder()
                .roleEnum(RoleEnum.CLIENT_ROLE)
                .build();
        roles.add(clientRole);
        return roles;
    }
}