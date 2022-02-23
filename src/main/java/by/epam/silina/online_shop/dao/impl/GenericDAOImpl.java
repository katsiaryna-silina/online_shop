package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.config.DAOIdentifier;
import by.epam.silina.online_shop.dao.GenericDAO;
import by.epam.silina.online_shop.datasource.InMemoryDataSource;

import java.util.Map;
import java.util.Optional;

public class GenericDAOImpl<T extends DAOIdentifier> implements GenericDAO<T> {
    private final Map<Long, T> map;
    private Long maxId = 0L;

    public GenericDAOImpl(Class<T> type) {
        this.map = InMemoryDataSource.getInstance().getMap(type);
    }

    public void incrementMaxId() {
        ++maxId;
    }

    @Override
    public Optional<T> get(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Map<Long, T> getAll() {
        return map;
    }

    @Override
    public void save(T t) {
        incrementMaxId();
        //calling method from DAOIdentifier
        t.setId(maxId);
        map.put(maxId, t);
    }

    @Override
    public void update(Long id, T t) {
        map.put(id, t);
    }

    @Override
    public void delete(Long id) {
        map.remove(id);
    }
}
