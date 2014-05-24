package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.TeamController;
import ch.fhnw.swa.turnier.domain.Team;
import javax.faces.convert.FacesConverter;

/**
 * Converter for the team entity.
 */
@FacesConverter(forClass = Team.class)
public class TeamConverter extends AbstractConverter<Team, TeamController> {

    /**
     * Constructor.
     */
    public TeamConverter() {
        super(Team.class, "teamController");
    }
    
}
