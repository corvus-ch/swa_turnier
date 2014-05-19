package ch.fhnw.swa.turnier.domain;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbstractEntity)) {
            return false;
        }

        if (object.getClass() != this.getClass()) {
            return false;
        }

        AbstractEntity other = (AbstractEntity) object;

        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    
    @Override
    public String toString() {
        return this.getClass() + "[ id=" + getId() + " ]";
    }
}
