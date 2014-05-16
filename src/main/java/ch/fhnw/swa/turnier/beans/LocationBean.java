package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Location;

public class LocationBean extends AbstractBean<Location> {

    public LocationBean() {
        super(Location.class, "Location.findAll");
    }
}
