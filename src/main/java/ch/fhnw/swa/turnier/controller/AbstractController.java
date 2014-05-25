package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.utils.JsfUtil;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 * Abstract impementation of the controller interface.
 * 
 * Prevents the need for writing a lot of duplicated code.
 * Implements all methods of
 * {@code ch.fhnw.swa.turnier.controller.ControllerInterface} that can be
 * handled in an abstract manner leaving all the entity specific
 * stuff to the entity specific implementations.
 *
 * In order to alter the create, update and destroy behaviour, one can overwrite
 * the corresponding doX() methods.
 *
 * @param <T> 
 *   The entity the controller supports.
 */
public abstract class AbstractController<T> implements ControllerInterface<T> {

    /**
     * The current entity.
     */
    private T current;

    /**
     * The entity list.
     */
    private DataModel items = null;

    /**
     * The entity class.
     *
     * We can not get the entity class from an abstract type. So we pass it in.
     * This property must be set in the contstrucotr by the implementing class.
     */
    protected Class<T> entityClass;

    /**
     * {@inheritDoc}
     */
    @Override
    public T getCurrent() {
        if (current == null) {
            try {
                current = entityClass.newInstance();
            } catch ( InstantiationException | IllegalAccessException e) {
                JsfUtil.addErrorMessage(e, "Could not set current entity.");
            }
        }
        return current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrent(T entity) {
        this.current = entity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String prepareList() {
        recreateModel();
        return "list";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String prepareCreate() {
        String next;
        try {
            current = entityClass.newInstance();
            next = "create";
        } catch (InstantiationException | IllegalAccessException e) {
            JsfUtil.addErrorMessage(e, "Failed to prepare for create.");
            next = "list";
        }
        return next;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String create() {
        try {
            doCreate();
            JsfUtil.addSuccessMessage("Entity created");
            prepareCreate();
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Could not create entity.");
            return null;
        }
    }

    /**
     * Does the actuall create operations.
     */
    protected void doCreate() {
        getBean().create(current);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String prepareEdit() {
        current = (T) getItems().getRowData();
        return "edit";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String update() {
        try {
            doUpdate();
            JsfUtil.addSuccessMessage("Entity updated.");
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Could not update entity.");
            return null;
        }
    }

    /**
     * Does the actuall update operations.
     */
    protected void doUpdate() {
        getBean().update(current);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String destroy() {
        current = (T) getItems().getRowData();
        try {
            doDestroy();
            JsfUtil.addSuccessMessage("Entity deleted.");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Failed to delete!");
        }
        recreateModel();
        return "list";
    }

    /**
     * Does the actuall destroy operations.
     */
    protected void doDestroy() {
        getBean().delete(current);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(getBean().findAll());
        }
        return items;
    }

    /**
     * Sets the model to {@code null}.
     *
     * The model will be recreated when requested next.
     *
     * @see AbstractController::getItems()
     */
    protected void recreateModel() {
        items = null;
    }
}
