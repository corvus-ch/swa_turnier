package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Team;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CRUD bean for teams.
 */
@Stateless
public class TeamBean extends AbstractBean<Team> {

    /**
     * The entity manger.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Construcotr.
     */
    public TeamBean() {
        super(Team.class, "Team.findAll");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }   
}
