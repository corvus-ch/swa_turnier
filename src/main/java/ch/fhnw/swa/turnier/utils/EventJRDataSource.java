package ch.fhnw.swa.turnier.utils;

import ch.fhnw.swa.turnier.domain.Event;
import java.util.Iterator;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 * Jasper report datasource for evets.
 * 
 * This data source supports the following list of fields:
 *   - begin
 *   - end
 *   - type
 *   - location
 *   - team
 *   - coach
 *
 * The fields are returned as their native data type and must be converted in
 * the report as needed.
 */
public class EventJRDataSource implements JRDataSource {

    /**
     * The current event.
     */
    private Event current;

    /**
     * The event iterator.
     */
    private final Iterator<Event> iterator;

    /**
     * Constructor.
     *
     * @param iterator
     *   The event iterator.
     */
    public EventJRDataSource(Iterator<Event> iterator) {
        this.iterator = iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean next() throws JRException {
        boolean hasNext = iterator.hasNext();
        if (hasNext) {
            current = iterator.next();
        }
        return hasNext;
    }

    /**
     * {@inheritDoc}
     */
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
