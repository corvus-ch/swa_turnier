package ch.fhnw.swa.turnier.utils.converter;

import ch.fhnw.swa.turnier.controller.TeamController;
import ch.fhnw.swa.turnier.domain.Team;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Team.class)
public class TeamConverter extends AbstractConverter<Team, TeamController> {

    public TeamConverter() {
        super(Team.class, "teamController");
    }
    
}
