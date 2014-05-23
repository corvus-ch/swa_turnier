package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import java.io.Serializable;
import javax.faces.model.DataModel;

public interface ControllerInterface<T> extends Serializable {
    
    public T getCurrent();

    public void setCurrent(T entity);

    public DataModel getItems();

    public CrudBeanInterface getBean();

    public String prepareList();

    public String prepareCreate();

    public String prepareEdit();

    public String create();

    public String update();

    public String destroy();
}
