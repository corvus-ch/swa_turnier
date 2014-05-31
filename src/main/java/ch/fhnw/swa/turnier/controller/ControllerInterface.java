package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import java.io.Serializable;
import javax.faces.model.DataModel;

/**
 * Controler inteface.
 *
 * Interface for a controller supporting crud operations for an entity.
 *
 * @param <T>
 *   The supported entity.
 */
public interface ControllerInterface<T> extends Serializable {
    
    /**
     * Gets the current entity.
     *
     * A working copy of the entity used when creating or editing.
     *
     * @return
     *   Teh current entity.
     */
    public T getCurrent();

    /**
     * Sets teh current entity.
     *
     * @param entity
     *   The new entity.
     */
    public void setCurrent(T entity);

    /**
     * Gets teh lsit of items.
     *
     * Faces data model containg a list of all entities available.
     *
     * @return
     *   The list of all entites.
     */
    public DataModel getItems();

    /**
     * Gets the crud bean.
     *
     * @return
     *   The crud bean for the entity.
     */
    public CrudBeanInterface getBean();

    /**
     * Prepares the list view.
     *
     * @return
     *   The name of the view.
     */
    public String prepareList();

    /**
     * Prepares the create view.
     * 
     * @return
     *   The name of the view.
     */
    public String prepareCreate();

    /**
     * Preares the edit view.
     * 
     * @return
     *   The name of the view.
     */
    public String prepareEdit();

    /**
     * Creates a new entity.
     *
     * @return
     *   The name of the next view to display.
     */
    public String create();

    /**
     * Updates an entity.
     * 
     * @return
     *   The name of the next view to display.
     */
    public String update();

    /**
     * Deletes an entity.
     * 
     * @return
     *   The name of the next view to display.
     */
    public String destroy();
}
