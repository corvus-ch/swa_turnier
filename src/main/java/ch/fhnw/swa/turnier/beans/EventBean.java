package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Event;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EventBean extends AbstractBean<Event> {

    @PersistenceContext
    private EntityManager em;

    public EventBean() {
        super(Event.class, "Event.findAll");
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
}
