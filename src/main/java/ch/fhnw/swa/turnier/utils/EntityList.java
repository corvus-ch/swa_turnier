package ch.fhnw.swa.turnier.utils;

import ch.fhnw.swa.turnier.domain.AbstractEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Entity list.
 *
 * Populates changes of an array list containing entites to the owning siede of
 * a many to many relationship.
 *
 * @param <T>
 *   The type of entities in this list.
 * @param <E>
 *   The non owning entity class of the relationship.
 */
abstract public class EntityList<T extends AbstractEntity, E extends AbstractEntity> extends ArrayList<T> {

    /**
     * The non owning entity class of the relationship.
     */
    protected final E entity;

    /**
     * Constructor.
     *
     * @param entity
     *   The non owning entity of the relationship.
     * @param initialCapacity
     *   The initial list capacity.
     */
    public EntityList(E entity, int initialCapacity) {
        super(initialCapacity);
        this.entity = entity;
    }

    /**
     * Constructor.
     *
     * @param entity
     *   The non owning entity of the relationship.
     */
    public EntityList(E entity) {
        this.entity = entity;
    }

    /**
     * Constructor.
     *
     * @param entity
     *   The non owning entity of the relationship.
     * @param c
     *   Collection of initial list entries.
     */
    public EntityList(E entity, Collection<? extends T> c) {
        super(c);
        this.entity = entity;
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        for (T t : this) {
            List<E> list = getOwnlingList(t);
            if (list.contains(entity)) {
                list.remove(entity);
            }
        }
        super.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(T e) {
        List<E> list = getOwnlingList(e);
        if (!list.contains(entity)) {
            list.add(entity);
        }
        return super.add(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the list of the owning side.
     *
     * @param entity
     *   The owning side entity.
     *
     * @return 
     *   The list from the owning side.
     */
    abstract protected List<E> getOwnlingList(T entity);
}
