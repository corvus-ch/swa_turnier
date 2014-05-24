package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Event;
import ch.fhnw.swa.turnier.domain.Location;
import ch.fhnw.swa.turnier.domain.Team;
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void mergeRelated(Event entity) {
        Location l = entity.getLocation();
        l = getEm().merge(l);
        entity.setLocation(l);
        Team t = entity.getTeam();
        t = getEm().merge(t);
        entity.setTeam(t);
    }
}
