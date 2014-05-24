package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CRUD bean for people.
 */
@Stateless
public class PersonBean extends AbstractBean<Person> {

    /**
     * The entit manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Construcotr.
     */
    public PersonBean() {
        super(Person.class, "Person.findAll");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEm() {
        return em;
    } 
}
