package ch.fhnw.swa.turnier.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    @NotNull
    @Future
    private Date begin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    @NotNull
    @Future
    private Date end;

    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType type;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location location;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Team team;

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
