package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.LocationBean;
import ch.fhnw.swa.turnier.domain.Location;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Controller for the location entity.
 */
@Named
@SessionScoped
public class LocationController extends AbstractController<Location>{

    /**
     * The CRUD bean for locations.
     */
    @EJB
    private LocationBean bean;

    /**
     * Constructor.
     */
    public LocationController() {
        entityClass = Location.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
