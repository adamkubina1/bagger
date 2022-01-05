package cz.vse.bagger.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Team {
    private IntegerProperty idTeam;
    private IntegerProperty idLeader;
    private StringProperty teamName;

    public Team() {
        this.idTeam = new SimpleIntegerProperty();
        this.idLeader = new SimpleIntegerProperty();
        this.teamName = new SimpleStringProperty();
    }

    public int getIdTeam() {
        return idTeam.get();
    }

    public IntegerProperty idTeamProperty() {
        return idTeam;
    }

    public void setIdTeam(int idTeam) {
        this.idTeam.set(idTeam);
    }

    public int getIdLeader() {
        return idLeader.get();
    }

    public IntegerProperty idLeaderProperty() {
        return idLeader;
    }

    public void setIdLeader(int idLeader) {
        this.idLeader.set(idLeader);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public StringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }
}
