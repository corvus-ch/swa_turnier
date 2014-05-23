package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.LocationController;
import ch.fhnw.swa.turnier.domain.Location;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Location.class)
public class LocationConverter extends AbstractConverter<Location, LocationController> {

    public LocationConverter() {
        super(Location.class, "locationController");
    }
    
}
