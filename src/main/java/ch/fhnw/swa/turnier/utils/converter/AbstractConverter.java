package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.ControllerInterface;
import ch.fhnw.swa.turnier.domain.AbstractEntity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

abstract public class AbstractConverter<E extends AbstractEntity, C extends ControllerInterface<E>> implements Converter {

    protected Class<E> entityClass;

    protected String controllerName;

    public AbstractConverter(Class<E> entityClass, String controllerName) {
        this.entityClass = entityClass;
        this.controllerName = controllerName;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }

        C controller = (C) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, controllerName);
        Long id = Long.valueOf(value);

        Object object = controller.getBean().find(id);
        return object;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }

        if (entityClass.isInstance(object)) {
            E o = (E) object;
            String string = o.getId().toString();
            return string;
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + entityClass.getName());
        }
    }
}
