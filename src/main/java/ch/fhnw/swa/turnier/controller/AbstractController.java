package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.utils.JsfUtil;
import ch.fhnw.swa.turnier.utils.PaginationHelper;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

public abstract class AbstractController<T> implements ControllerInterface<T> {

    private T current;

    private DataModel items = null;

    protected Class<T> entityClass;

    private PaginationHelper pagination;

    private int selectedItemIndex;

    @Override
    public T getCurrent() {
        if (current == null) {
            try {
                current = entityClass.newInstance();
                selectedItemIndex = -1;
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
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getBean().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getBean().findRange(getPageFirstItem(), getPageFirstItem() + getPageSize()));
                }
            };
        }
        return pagination;
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
            selectedItemIndex = -1;
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
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "Could not create entity.");
            return null;
        }
    }

    @Override
    public String prepareEdit() {
        current = (T) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
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
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
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

    private void updateCurrentItem() {
        int count = getBean().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = (T) getBean().findRange(selectedItemIndex, selectedItemIndex + 1).get(0);
        }
    }

    @Override
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    @Override
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "list";
    }

    @Override
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "list";
    }
}
