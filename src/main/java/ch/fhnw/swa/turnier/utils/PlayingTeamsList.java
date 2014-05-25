package ch.fhnw.swa.turnier.utils;

import ch.fhnw.swa.turnier.domain.Person;
import ch.fhnw.swa.turnier.domain.Team;
import java.util.Collection;
import java.util.List;

/**
 * Propagating entity list for playing teams.
 */
public class PlayingTeamsList extends EntityList<Team, Person> {

    /**
     * {@inheritDoc}
     */
    public PlayingTeamsList(Person entity, int initialCapacity) {
        super(entity, initialCapacity);
    }

    /**
     * {@inheritDoc}
     */
    public PlayingTeamsList(Person entity) {
        super(entity);
    }

    /**
     * {@inheritDoc}
     */
    public PlayingTeamsList(Person entity, Collection<? extends Team> c) {
        super(entity, c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<Person> getOwnlingList(Team entity) {
        return entity.getPlayers();
    }
}
