package ch.fhnw.swa.turnier.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person  extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String mail;

    private String phone;

    private String address;

    @ManyToMany(mappedBy = "coaches")
    private List<Team> coaches = new ArrayList<>();

    @ManyToMany(mappedBy = "players")
    private List<Team> plays = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Team> getCoachedTeams() {
        return coaches;
    }

    public void setCoachedTeams(List<Team> teams) {
        this.coaches = teams;
    }

    public void addCoachedTeam(Team team) {
        this.coaches.add(team);
    }

    public void removeCoachedTeam(Team team) {
        this.coaches.remove(team);
    }

    public List<Team> getTeams() {
        return plays;
    }

    public void setTeams(List<Team> teams) {
        this.plays = teams;
    }

    public void addTeam(Team team) {
        this.plays.add(team);
    }

    public void removeTeam(Team team) {
        this.plays.remove(team);
    }
}
