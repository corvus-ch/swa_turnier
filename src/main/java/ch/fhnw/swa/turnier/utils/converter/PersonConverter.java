package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.PersonController;
import ch.fhnw.swa.turnier.domain.Person;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Person.class)
public class PersonConverter extends AbstractConverter<Person, PersonController> {

    public PersonConverter() {
        super(Person.class, "personController");
    }
}
