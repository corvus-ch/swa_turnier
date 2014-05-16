package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Location;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LocationBean extends AbstractBean<Location> {

    @PersistenceContext
    private EntityManager em;

    public LocationBean() {
        super(Location.class, "Location.findAll");
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }
}
