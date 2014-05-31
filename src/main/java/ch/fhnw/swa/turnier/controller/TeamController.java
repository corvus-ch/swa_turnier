package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.TeamBean;
import ch.fhnw.swa.turnier.domain.Team;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Controller for teams.
 */
@Named
@SessionScoped
public class TeamController extends AbstractController<Team> {

    /**
     * The CRUD bean for teams.
     */
    @EJB
    private TeamBean bean;

    /**
     * Construcotr.
     */
    public TeamController() {
        entityClass = Team.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
