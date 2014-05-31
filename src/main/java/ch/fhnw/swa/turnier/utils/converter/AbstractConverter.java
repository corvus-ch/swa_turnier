package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.ControllerInterface;
import ch.fhnw.swa.turnier.domain.AbstractEntity;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Abstract entity converter.
 *
 * Abstract implementation of {@code javax.faces.convert.Converter} using the
 * entities id as string representation. Gettign as object is done by loocking
 * up the controller for the entity and using the find method on the entity
 * bean.
 *
 * @param <E>
 *   The entity this converter is responsible for.
 * @param <C>
 *   The controller targeted for the entntity.
 */
abstract public class AbstractConverter<E extends AbstractEntity, C extends ControllerInterface<E>> implements Converter {

    /**
     * The entity class.
     */
    protected Class<E> entityClass;

    /**
     * The controller name.
     */
    protected String controllerName;

    /**
     * Constructor.
     *
     * Getting classes from generics is not possible so we need to pass them in
     * using the constructor.
     *
     * For looking up the controller in the faces context, we need its name.
     *
     * @param entityClass
     *   The entity class.
     * @param controllerName 
     *   The controller name.
     */
    public AbstractConverter(Class<E> entityClass, String controllerName) {
        this.entityClass = entityClass;
        this.controllerName = controllerName;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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
