package ch.fhnw.swa.turnier.domain;

import ch.fhnw.swa.turnier.utils.CoachedTeamsList;
import ch.fhnw.swa.turnier.utils.EntityList;
import ch.fhnw.swa.turnier.utils.PlayingTeamsList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The person entity.
 *
 * A person has a name and can have several means for contacting the person set.
 * A perason can be assigned to a team eighter as coach and or palyer.
 *
 * The entity uses implementations of {@code EntityList} to propagate changes to
 * manay to many relationships to the owning side.
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person  extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    /**
     * The name.
     */
    @NotNull
    @Size(min = 2, message = "Must be at least two characters.")
    private String name;

    /**
     * The email address.
     */
    private String mail;

    /**
     * The phone number.
     */
    private String phone;

    /**
     * The address
     */
    private String address;

    /**
     * List of teams coached by this person.
     */
    @ManyToMany(mappedBy = "coaches", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Team> coaches = new CoachedTeamsList(this);

    /**
     * List of teams this person plays with.
     */
    @ManyToMany(mappedBy = "players", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Team> plays = new PlayingTeamsList(this);

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
     * Gets the mail addrees.
     *
     * @return
     *   The mail address.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the mail address.
     *
     * @param mail
     *   The new mail address.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the phone number.
     *
     * @return
     *   The phone number.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number.
     *
     * @param phone
     *   The new phone number.
     */
    public void setPhone(String phone) {
        this.phone = phone;
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

    /**
     * Gets the list of coached teams.
     *
     * @return
     *   List of teams coached by this person.
     */
    public List<Team> getCoachedTeams() {
        // Ensure propagation to owning side does work.
        if (!(coaches instanceof EntityList)) {
            coaches = new CoachedTeamsList(this, coaches);
        }
        return coaches;
    }

    /**
     * Sets the list of coached teams.
     *
     * @param teams
     *   The new list of teams coached by this person.
     */
    public void setCoachedTeams(List<Team> teams) {
        this.coaches = teams;
    }

    /**
     * Gets the list of teams.
     *
     * @return
     *   Teh list of teams this person plays with.
     */
    public List<Team> getTeams() {
        // Ensure propagation to owning side does work.
        if (!(plays instanceof EntityList)) {
            plays = new CoachedTeamsList(this, plays);
        }
        return plays;
    }

    /**
     * Sets the list of playing teams.
     *
     * @param teams
     *   The new list of teams this person plays with.
     */
    public void setTeams(List<Team> teams) {
        this.plays = teams;
    }
}
