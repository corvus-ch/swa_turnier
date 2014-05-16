package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Person;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonBean extends AbstractBean<Person> {

    @PersistenceContext
    private EntityManager em;

    public PersonBean() {
        super(Person.class, "Person.findAll");
    }

    @Override
    protected EntityManager getEm() {
        return em;
    } 
}
