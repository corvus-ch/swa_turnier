package ch.fhnw.swa.turnier.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
