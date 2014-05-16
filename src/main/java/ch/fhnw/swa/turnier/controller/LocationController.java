package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.LocationBean;
import ch.fhnw.swa.turnier.domain.Location;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LocationController extends AbstractController<Location>{

    @EJB
    private LocationBean bean;

    public LocationController() {
        entityClass = Location.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
