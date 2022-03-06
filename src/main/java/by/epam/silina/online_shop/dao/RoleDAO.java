package by.epam.silina.online_shop.dao;

import by.epam.silina.online_shop.model.Role;

public interface RoleDAO extends GenericDAO<Role> {
    Role getRoleByName(String roleName);
}
