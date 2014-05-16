package ch.fhnw.swa.turnier.beans;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

abstract public class AbstractBean<T> implements CrudBeanInterface<T> {
    
    final Class<T> entityClass;

    final String findAllQueryName;
    
    @PersistenceContext
    private EntityManager em;

    public AbstractBean(Class<T> entityClass, String findAllQueryName) {
        this.entityClass = entityClass;
        this.findAllQueryName = findAllQueryName;
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = em.createNamedQuery(findAllQueryName, entityClass);
        return query.getResultList();
    }

    @Override
    public T findById(Long id) {
        return em.find(this.entityClass, id);
    }

    @Override
    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }
}
