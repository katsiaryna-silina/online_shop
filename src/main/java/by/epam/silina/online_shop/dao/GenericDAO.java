package by.epam.silina.online_shop.dao;

import by.epam.silina.online_shop.exception.GenericDAOImplException;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T> {
    Optional<T> get(Long id) throws GenericDAOImplException;

    List<T> getAll();

    void save(T t);

    void update(Long id, T t);

    void delete(Long id);

    Long getEntityCount();
}