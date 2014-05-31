package ch.fhnw.swa.turnier.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The location entity.
 *
 * Location entity that can be assigned to events.
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * The name.
     */
    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
    private String name;

    /**
     * The address.
     */
    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
    private String address;

    /**
     * Gets the name.
     *
     * @return
     *   The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name
     *   The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address.
     *
     * @return
     *   The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address.
     *
     * @param address
     *   The new address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
