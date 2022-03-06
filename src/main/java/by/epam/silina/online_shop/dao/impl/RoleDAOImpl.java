package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.dao.RoleDAO;
import by.epam.silina.online_shop.model.Role;

/**
 * Role dao implementation responsible for getting role's data from datasource.
 */
public class RoleDAOImpl extends GenericDAOImpl<Role> implements RoleDAO {
    private static final RoleDAO instance = new RoleDAOImpl();

    private RoleDAOImpl() {
        super(Role.class);
    }

    public static RoleDAO getInstance() {
        return instance;
    }

    /**
     * Depending on role name, requests data of all roles and returns appropriate role.
     *
     * @param roleName string of role name
     * @return Role
     */
    @Override
    public Role getRoleByName(String roleName) {
        return getAll().stream()
                .filter(el -> el.getRoleEnum().getRoleDescription().equals(roleName))
                .findFirst().orElse(null);
    }
}
