package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.EventController;
import ch.fhnw.swa.turnier.domain.Event;
import javax.faces.convert.FacesConverter;

/**
 * Converter for the event entity.
 */
@FacesConverter(forClass = Event.class)
public class EventConverter extends AbstractConverter<Event, EventController> {

    /**
     * Constructor.
     */
    public EventConverter() {
        super(Event.class, "eventController");
    }
    
}
