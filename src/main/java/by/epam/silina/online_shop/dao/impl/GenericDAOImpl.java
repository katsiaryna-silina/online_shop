package by.epam.silina.online_shop.dao.impl;

import by.epam.silina.online_shop.config.DAOIdentifier;
import by.epam.silina.online_shop.dao.GenericDAO;
import by.epam.silina.online_shop.datasource.InMemoryDataSource;
import by.epam.silina.online_shop.exception.GenericDAOImplException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Generic dao implementation responsible for getting data from datasource for entities that implements DAOIdentifier interface.
 */
@Slf4j
public abstract class GenericDAOImpl<T extends DAOIdentifier> implements GenericDAO<T> {
    private List<T> entityList;
    private Long maxId = 0L;
    private Long entityCount = 0L;

    protected GenericDAOImpl(Class<T> type) {
        this.entityList = InMemoryDataSource.getInstance().getEntityList(type);
    }

    public void incrementMaxId() {
        ++maxId;
    }

    /**
     * Depending on entity id, requests data of entity with this id and returns an optional object of the entity.
     *
     * @param id entity's id
     * @return Optional<T>
     */
    @Override
    public Optional<T> get(Long id) throws GenericDAOImplException {
        if (id == null) {
            throw new GenericDAOImplException("Id cannot be null.");
        } else {
            return getAll().stream()
                    .filter(el -> el.getId().equals(id))
                    .findFirst();
        }
    }

    /**
     * Requests data of all entity objects and returns list of entity objects.
     *
     * @return List<T>
     */
    @Override
    public List<T> getAll() {
        return entityList;
    }

    /**
     * Saves entity object with all it's data to the datasource.
     * Calls method from DAOIdentifier setId() to set id to the entity object.
     *
     * @param t entity object
     */
    @Override
    public void save(T t) {
        incrementMaxId();
        //calling method from DAOIdentifier
        t.setId(maxId);
        entityList.add(t);
        entityCount++;
    }

    /**
     * Updates an entity object with definite id to entity object passed in parameters.
     *
     * @param id entity's id
     * @param t  updated entity object
     */
    @Override
    public void update(Long id, T t) {
        entityList.stream()
                .filter(el -> el.getId().equals(id))
                .forEach(el -> el = t);
    }

    /**
     * Deletes an entity object with definite id.
     *
     * @param id entity's id
     */
    @Override
    public void delete(Long id) {
        entityList = entityList.stream()
                .filter(el -> !el.getId().equals(id))
                .collect(Collectors.toList());
        entityCount--;
    }

    /**
     * Requests data of entity objects count and returns this number.
     *
     * @return Long
     */
    public Long getEntityCount() {
        return entityCount;
    }
}
