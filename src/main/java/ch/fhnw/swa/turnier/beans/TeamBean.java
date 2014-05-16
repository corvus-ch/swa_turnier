package ch.fhnw.swa.turnier.beans;

import ch.fhnw.swa.turnier.domain.Team;

public class TeamBean extends AbstractBean<Team> {

    public TeamBean() {
        super(Team.class, "Team.findAll");
    }
}