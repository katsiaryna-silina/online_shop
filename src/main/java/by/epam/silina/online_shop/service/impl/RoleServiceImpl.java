package by.epam.silina.online_shop.service.impl;

import by.epam.silina.online_shop.dao.RoleDAO;
import by.epam.silina.online_shop.dao.impl.RoleDAOImpl;
import by.epam.silina.online_shop.model.Role;
import by.epam.silina.online_shop.service.RoleService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Service for role logic responsible for processing role data from dao layer.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {
    private static final RoleService instance = new RoleServiceImpl();
    private final RoleDAO roleDAO = RoleDAOImpl.getInstance();

    public static RoleService getInstance() {
        return instance;
    }

    /**
     * Depending on role name, requests data of role with this name from role dao
     * and returns this role object.
     *
     * @param roleName role name
     * @return Role
     */
    @Override
    public Role getRoleByName(String roleName) {
        return roleDAO.getRoleByName(roleName);
    }
}
