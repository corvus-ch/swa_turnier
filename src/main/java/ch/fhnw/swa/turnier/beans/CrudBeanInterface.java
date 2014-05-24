package ch.fhnw.swa.turnier.beans;

import java.util.List;

/**
 * Interface for beans supporting CRUD operstions of enties.
 *
 * @param <T> 
 *   The entity the bean supports.
 */
public interface CrudBeanInterface<T> {

    /**
     * Finds all entities.
     *
     * @return
     *   List of all entites.
     */
    public List<T> findAll();

    /**
     * Finds a single entity.
     *
     * @param id
     *   The entities identifier.
     *
     * @return
     *   The entity.
     */
    public T find(Long id);

    /**
     * Persists an entity.
     *
     * @param entity
     *   The entity to persist.
     *
     * @return
     *   The persisted entity.
     */
    public T create(T entity);

    /**
     * Updates an entity.
     *
     * @param entity
     *   The entity to update.
     *
     * @return
     *   The updated entity.
     */
    public T update(T entity);

    /**
     * Removes an entity.
     *
     * @param entity
     *   The entity to remove.
     */
    public void delete(T entity);
}
