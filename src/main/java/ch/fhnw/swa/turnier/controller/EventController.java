package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.EventBean;
import ch.fhnw.swa.turnier.domain.Event;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class EventController extends AbstractController<Event> {

    @EJB
    private EventBean bean;

    public EventController() {
        entityClass = Event.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
