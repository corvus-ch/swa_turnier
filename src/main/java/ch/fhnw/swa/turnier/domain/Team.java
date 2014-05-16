package ch.fhnw.swa.turnier.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
public class Team extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;

    private String name;

    @ManyToMany
    private List<Person> coaches = new ArrayList<>();

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
}
