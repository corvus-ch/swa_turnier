package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Event;
import javax.ejb.Stateless;

@Stateless
public class EventBean extends BaseBean<Event> {

    public EventBean() {
        super(Event.class, "Event.findAll");
    }
}
