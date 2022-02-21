package by.epam.silina.online_shop.dao;

import java.util.Map;
import java.util.Optional;

public interface GenericDAO<T> {
    Optional<T> get(Long id);

    Map<Long, T> getAll();

    void save(T t);

    void update(Long id, T t);

    void delete(Long id);
}