package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.PersonController;
import ch.fhnw.swa.turnier.domain.Person;
import javax.faces.convert.FacesConverter;

/**
 * Converter for the person entity.
 */
@FacesConverter(forClass = Person.class)
public class PersonConverter extends AbstractConverter<Person, PersonController> {

    /**
     * Constructor.
     */
    public PersonConverter() {
        super(Person.class, "personController");
    }
}
