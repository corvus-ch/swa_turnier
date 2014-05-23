package ch.fhnw.swa.turnier.controller;

import ch.fhnw.swa.turnier.beans.CrudBeanInterface;
import ch.fhnw.swa.turnier.beans.TeamBean;
import ch.fhnw.swa.turnier.domain.Team;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class TeamController extends AbstractController<Team> {

    @EJB
    private TeamBean bean;

    @Inject
    private PersonController personController;

    private List<AbstractController> others;

    public TeamController() {
        entityClass = Team.class;
    }

    @Override
    public CrudBeanInterface getBean() {
        return bean;
    }

    @Override
    public List<AbstractController> getOthers() {
        if (others == null) {
            others = super.getOthers();
            others.add(personController);
        }
        return others;
    }
}
