package ch.fhnw.swa.turnier.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The team entity.
 *
 * A team has a name and can have coaches and players assigned.
 */
@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;

    /**
     * The name.
     */
    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
    private String name;

    /**
     * The list of coaches.
     */
    @ManyToMany
    @JoinTable(
            name = "coaches",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> coaches = new ArrayList<>();

    /**
     * The list of players.
     */
    @ManyToMany
    @JoinTable(
            name = "players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> players = new ArrayList<>();

    /**
     * Gets the name.
     *
     * @return
     *   Teh name.
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
     * Gets the coaches.
     *
     * @return
     *   The list of coaches.
     */
    public List<Person> getCoaches() {
        return coaches;
    }

    /**
     * Sets the list of coaches.
     *
     * @param coaches
     *   The new list of coaches.
     */
    public void setCoaches(List<Person> coaches) {
        this.coaches = coaches;
    }

    /**
     * Gets the players.
     *
     * @return
     *   The list of players.
     */
    public List<Person> getPlayers() {
        return players;
    }

    /**
     * Sets the player.
     *
     * @param players
     *   The new list of players.
     */
    public void setPlayers(List<Person> players) {
        this.players = players;
    }
}
