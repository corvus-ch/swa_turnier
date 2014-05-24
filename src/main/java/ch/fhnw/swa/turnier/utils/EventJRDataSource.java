package ch.fhnw.swa.turnier.utils;

import ch.fhnw.swa.turnier.domain.Event;
import java.util.Iterator;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class EventJRDataSource implements JRDataSource {

    private Event current;

    private final Iterator<Event> iterator;

    public EventJRDataSource(Iterator<Event> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean next() throws JRException {
        boolean hasNext = iterator.hasNext();
        if (hasNext) {
            current = iterator.next();
        }
        return hasNext;
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object value = null;
        try {
            switch (jrf.getName()) {
                case "begin":
                    value = current.getBegin();
                    break;
                case "end":
                    value = current.getBegin();
                    break;
                case "type":
                    value = current.getType();
                    break;
                case "location":
                    value = current.getLocation();
                    break;
                case "team":
                    value = current.getTeam();
                    break;
                case "coach":
                    value = current.getTeam().getCoaches().get(0);
                    break;
                default:
                    throw new JRException("Unknown field: " + jrf.getName());
            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            // Intentionally left blank.
        }
        return value;
    }
};
