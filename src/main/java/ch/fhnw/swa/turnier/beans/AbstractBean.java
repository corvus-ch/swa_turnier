package ch.fhnw.swa.turnier.beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

abstract public class AbstractBean<T> implements CrudBeanInterface<T> {

    final Class<T> entityClass;

    final String findAllQueryName;

    public AbstractBean(Class<T> entityClass, String findAllQueryName) {
        this.entityClass = entityClass;
        this.findAllQueryName = findAllQueryName;
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = getEm().createNamedQuery(findAllQueryName, entityClass);
        return query.getResultList();
    }

    @Override
    public T find(Long id) {
        return getEm().find(this.entityClass, id);
    }

    @Override
    public T create(T entity) {
        mergeRelated(entity);
        getEm().persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return getEm().merge(entity);
    }

    @Override
    public void delete(T entity) {
        entity = getEm().merge(entity);
        getEm().remove(entity);
    }

    @Override
    public List<T> findRange(int first, int last) {
        TypedQuery<T> query = getEm().createNamedQuery(findAllQueryName, entityClass);
        query.setMaxResults(last - first + 1);
        query.setFirstResult(first);
        return query.getResultList();
    }

    @Override
    public int count() {
        CriteriaQuery criterieQuery = getEm().getCriteriaBuilder().createQuery();
        Root<T> rt = criterieQuery.from(entityClass);
        criterieQuery.select(getEm().getCriteriaBuilder().count(rt));
        Query query = getEm().createQuery(criterieQuery);
        return ((Long) query.getSingleResult()).intValue();
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

    protected abstract EntityManager getEm();    
}
