package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.PersonBean;
import ch.fhnw.swa.turnier.beans.TeamBean;
import ch.fhnw.swa.turnier.domain.Person;
import ch.fhnw.swa.turnier.domain.Team;
import java.util.HashSet;
import java.util.Set;
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
     * The CRUD bean for teams.
     */
    @EJB
    private TeamBean teamBean;

    /**
     * Affected teams.
     *
     * Set of teams affected by changes to the current person.
     */
    private Set<Team> affectedTeams;

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

    /**
     * Collects affected teams.
     * 
     * Collects a set of teams affected by changes to the current person.
     */
    private void collectAffectedTeams() {
        if (affectedTeams == null) {
            affectedTeams = new HashSet<>();
        }
        affectedTeams.addAll(getCurrent().getCoachedTeams());
        affectedTeams.addAll(getCurrent().getTeams());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String prepareEdit() {
        String target = super.prepareEdit();
        collectAffectedTeams();
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String prepareCreate() {
        String target = super.prepareCreate();
        collectAffectedTeams();
        return target;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void recreateModel() {
        super.recreateModel();
        affectedTeams = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doDestroy() {
        super.doDestroy();
        updateTeams();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doUpdate() {
        super.doUpdate();
        updateTeams();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doCreate() {
        super.doCreate();
        updateTeams();
    }

    /**
     * Updated the affected teams.
     *
     * Iterate the set of affectedtams and updates them.
     *
     * Togehter with the {@code EntityList},this ensures proper persistence of
     * the many to many relationships.
     */
    private void updateTeams() {
        collectAffectedTeams();
        for (Team team : affectedTeams) {
            teamBean.update(team);
        }
        affectedTeams =null;
    }
}
