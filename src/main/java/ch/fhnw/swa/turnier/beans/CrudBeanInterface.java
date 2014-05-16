package ch.fhnw.swa.turnier.beans;

import java.util.List;

public interface CrudBeanInterface<T> {

    public int count();

    public List<T> findAll();

    public T find(Long id);

    public List<T> findRange(int first, int last);

    public T create(T entity);

    public T update(T entity);

    public void delete(T entity);
}
