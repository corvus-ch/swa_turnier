package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.PersonBean;
import ch.fhnw.swa.turnier.domain.Person;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class PersonController extends AbstractController<Person>{

    @EJB
    private PersonBean bean;

    @Inject
    private TeamController teamController;

    private List<AbstractController> others;

    public PersonController() {
        entityClass = Person.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }

    @Override
    public List<AbstractController> getOthers() {
        if (others == null) {
            others = super.getOthers();
            others.add(teamController);
        }
        return others;
    }
}
