package ch.fhnw.swa.turnier.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
    private String name;

    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
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
