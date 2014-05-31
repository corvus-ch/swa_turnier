package ch.fhnw.swa.turnier.beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Abstract impementation of the CRUD bean interface.
 * 
 * Prevents the need for writing a lot of duplicated code.
 * Implements all methods of {@code ch.fhnw.swa.turnier.beans.CrudBeanInterface}
 * that can be handled in an abstract manner leaving all the entity specific
 * stuff to the entity specific implementations.
 *
 * @param <T> 
 *   The entity the bean supports.
 */
abstract public class AbstractBean<T> implements CrudBeanInterface<T> {

    /**
     * The entity class.
     */
    final Class<T> entityClass;

    /**
     * The query name.
     * 
     * Name of the query that allows to find all entites.
     */
    final String findAllQueryName;

    /**
     * Constructor.
     *
     * Getting the class from an abstract type is not possible, so we pass it in
     * via the cosntructor.
     *
     * @param entityClass
     *   The entity class.
     * @param findAllQueryName
     *   Name of the query that allows to find all entites.
     */
    public AbstractBean(Class<T> entityClass, String findAllQueryName) {
        this.entityClass = entityClass;
        this.findAllQueryName = findAllQueryName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<T> findAll() {
        TypedQuery<T> query = getEm().createNamedQuery(findAllQueryName, entityClass);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T find(Long id) {
        return getEm().find(this.entityClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T create(T entity) {
        mergeRelated(entity);
        getEm().persist(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T update(T entity) {
        return getEm().merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(T entity) {
        entity = getEm().merge(entity);
        getEm().remove(entity);
    }

    /**
     * Merges the related entities.
     * 
     * 
     * For having CascadeType.MERGE set on the relationship annotations, does
     * not work when trying to persist a new entitie with detached entities
     * assigned.
     *
     * Implementations of this class can override this method to manually merge
     * related enties. The method will be called before a new entity gets
     * persisted.
     *
     * @param entity
     *   The entiy the related entites should be merged.
     *
     * @see AbstractBean::create()
     */
    protected void mergeRelated(T entity) {
        // Intentionaly left blank.
    }

    /**
     * Gets the entity manager.
     *
     * Injection is not possible on abstract classes. So we need to have the
     * entity manager injected to the implementations of this class.
     * This method allows to access this injected entity manager.
     *
     * @return
     *   The entity manager.
     */
    protected abstract EntityManager getEm();    
}
