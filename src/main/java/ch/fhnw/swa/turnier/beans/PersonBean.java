package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Person;

public class PersonBean extends AbstractBean<Person> {

    public PersonBean() {
        super(Person.class, "Person.findAll");
    }
}