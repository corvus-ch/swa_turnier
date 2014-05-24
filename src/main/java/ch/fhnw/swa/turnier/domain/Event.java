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

/**
 * The event entity.
 *
 * An event has a start and end date and can be of either game or training type.
 * Further an event can have a location and a team assigned.
 */
@Entity
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * The begin date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    @NotNull
    @Future
    private Date begin;

    /**
     * The end date.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    @NotNull
    @Future
    private Date end;

    /**
     * The event type.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventType type;

    /**
     * The location.
     *
     * Referenced location this event is taking place at.
     */
    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
    })
    private Location location;

    /**
     * The team.
     *
     * Refrenced team associated with this event.
     */
    @ManyToOne(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE,
    })
    private Team team;

    /**
     * Gets the begin date.
     *
     * @return
     *   The begin date.
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * Sets the bein date.
     *
     * @param begin
     *   The new begin date.
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * Gets the end date.
     *
     * @return
     *   The end date.
     */
    public Date getEnd() {
        return end;
    }

    /**
     * Sets the end date.
     *
     * @param end
     *   The new end date.
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     * Gets the event type.
     *
     * @return
     *   The event date.
     */
    public EventType getType() {
        return type;
    }

    /**
     * Sets the event type.
     *
     * @param type
     *   The new event type.
     */
    public void setType(EventType type) {
        this.type = type;
    }

    /**
     * Gets the location.
     *
     * @return
     *   The location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location
     *   The new location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the team.
     *
     * @return
     *   The team.
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the team.
     *
     * @param team
     *   The new team.
     */
    public void setTeam(Team team) {
        this.team = team;
    }
}
