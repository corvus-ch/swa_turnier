package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Team;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TeamBean extends AbstractBean<Team> {

    @PersistenceContext
    private EntityManager em;

    public TeamBean() {
        super(Team.class, "Team.findAll");
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }   
}
