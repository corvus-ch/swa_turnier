package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.utils.JsfUtil;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public abstract class AbstractController<T> implements ControllerInterface<T> {

    private T current;

    private DataModel items = null;

    protected Class<T> entityClass;

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

    @Override
    public void setCurrent(T entity) {
        this.current = entity;
    }

    @Override
    public String prepareList() {
        recreateModel();
        return "list";
    }

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

    @Override
    public String create() {
        try {
            getBean().create(current);
            JsfUtil.addSuccessMessage("Entity created");
            prepareCreate();
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Could not create entity.");
            return null;
        }
    }

    @Override
    public String prepareEdit() {
        current = (T) getItems().getRowData();
        return "edit";
    }

    @Override
    public String update() {
        try {
            getBean().update(current);
            JsfUtil.addSuccessMessage("Entity updated.");
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Could not update entity.");
            return null;
        }
    }

    @Override
    public String destroy() {
        current = (T) getItems().getRowData();
        performDestroy();
        recreateModel();
        return "list";
    }

    private void performDestroy() {
        try {
            getBean().delete(current);
            JsfUtil.addSuccessMessage("Entity deleted.");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Failed to delete!");
        }
    }

    @Override
    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(getBean().findAll());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }
}
