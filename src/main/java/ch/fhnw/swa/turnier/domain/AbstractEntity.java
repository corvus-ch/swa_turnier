package ch.fhnw.swa.turnier.domain;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract entity.
 *
 * Collection of properties and functionality common for all entities.
 */
@MappedSuperclass
abstract public class AbstractEntity implements Serializable {

    /**
     * The identifier.
     *
     * Numeric identifier (primary kay) of the entity.
     * The id can be lef blank. A value will be assigned when saving the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Gets the idenfier.
     *
     * @return
     *   The entites identifer.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifer.
     *
     * @param id
     *   The new idenfier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param object
     *   The reference object with which to compare.
     * @return
     *   {@code true} if this object is the same as the obj argument;
     *   {@code false} otherwise.
     *
     * @todo Warning - this won't work in the case the id fields are not set.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AbstractEntity)) {
            return false;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        AbstractEntity other = (AbstractEntity) object;

        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getClass() + "[ id=" + getId() + " ]";
    }
}
