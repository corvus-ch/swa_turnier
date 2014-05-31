package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Location;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CRUD bean for locations.
 */
@Stateless
public class LocationBean extends AbstractBean<Location> {

    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Construcotr.
     */
    public LocationBean() {
        super(Location.class, "Location.findAll");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }
}
