package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
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
