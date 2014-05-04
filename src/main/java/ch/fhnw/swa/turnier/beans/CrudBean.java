package ch.fhnw.swa.turnier.beans;

import java.util.List;

public interface CrudBean<T> {

    public List<T> findAll();

    public T findById(Long id);

    public T create(T entity);

    public T update(T entity);

    public void delete(T entity);
}
