package cz.vse.bagger.Models;

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
    private IntegerProperty Id_Team;
    private IntegerProperty Id_Leader;
    private StringProperty Team_Name;

    public Team() {
        this.Id_Team = new SimpleIntegerProperty();
        this.Id_Leader = new SimpleIntegerProperty();
        this.Team_Name = new SimpleStringProperty();
    }

    public int getId_Team() {
        return Id_Team.get();
    }

    public IntegerProperty id_TeamProperty() {
        return Id_Team;
    }

    public void setId_Team(int id_Team) {
        this.Id_Team.set(id_Team);
    }

    public int getId_Leader() {
        return Id_Leader.get();
    }

    public IntegerProperty id_LeaderProperty() {
        return Id_Leader;
    }

    public void setId_Leader(int id_Leader) {
        this.Id_Leader.set(id_Leader);
    }

    public String getTeam_Name() {
        return Team_Name.get();
    }

    public StringProperty team_NameProperty() {
        return Team_Name;
    }

    public void setTeam_Name(String team_Name) {
        this.Team_Name.set(team_Name);
    }
}
