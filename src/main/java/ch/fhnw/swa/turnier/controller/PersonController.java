package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.PersonBean;
import ch.fhnw.swa.turnier.domain.Person;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PersonController extends AbstractController<Person>{

    @EJB
    private PersonBean bean;

    public PersonController() {
        entityClass = Person.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
