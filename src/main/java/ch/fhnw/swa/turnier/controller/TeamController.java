package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.TeamBean;
import ch.fhnw.swa.turnier.domain.Team;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TeamController extends AbstractController<Team> {

    @EJB
    private TeamBean bean;

    public TeamController() {
        entityClass = Team.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }
}
