package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.PersonBean;
import ch.fhnw.swa.turnier.domain.Person;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Controller for the person entity.
 */
@Named
@SessionScoped
public class PersonController extends AbstractController<Person>{

    /**
     * The CRUD bean for persons.
     */
    @EJB
    private PersonBean bean;

    /**
     * Constructor.
     */
    public PersonController() {
        entityClass = Person.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
