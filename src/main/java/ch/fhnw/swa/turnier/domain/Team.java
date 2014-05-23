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

@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "coaches",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> coaches = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "players",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> players = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public List<Person> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Person> coaches) {
        this.coaches = coaches;
    }

    public void addCoach(Person coach) {
        this.coaches.add(coach);
    }

    public void removeCoach(Person coach) {
        this.coaches.remove(coach);
    }

    public List<Person> getPlayers() {
        return players;
    }

    public void setPlayers(List<Person> players) {
        this.players = players;
    }

    public void addPlayer(Person player) {
        this.players.add(player);
    }

    public void removePlayer(Person player) {
        this.players.remove(player);
    }
}
