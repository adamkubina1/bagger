package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Team
 */
public class TeamDao {
    /**
     *  Tato metoda vyhledá v databázi Team který má dané team Id
     *  @param Id_Team Id říká jaký tým chceme vyhledat
     */
    public static Team searchTeam (String Id_Team) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Team WHERE Id_Team="+Id_Team;

        try {
            ResultSet resultTeam = DBUtil.dbExecuteQuery(selectStmt);
            Team team = getTeamFromResultSet(resultTeam);
            return team;
        } catch (SQLException exception) {
            System.out.println("While searching team with " + Id_Team + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda vyhledá v databázi Team podle jeho vedoucího
     *  @param Id_Leader říká jaké id má daný leader abychom vyhledaly záznam
     */
    public static Team searchTeamOnLeader (String Id_Leader) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Team WHERE Id_Leader="+Id_Leader;

        try {
            ResultSet resultTeam = DBUtil.dbExecuteQuery(selectStmt);
            Team team = getTeamFromResultSet(resultTeam);
            return team;
        } catch (SQLException exception) {
            System.out.println("While searching team with " + Id_Leader + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda zpracovává výsledek ze searchTeam a  searchTeamOnLeader metody a záznamy které ta metoda vrátila dává do proměnné typu Team.
     *  @param resultTeam je výsledek který vrátila databáze na naše querry.
     */
    private static Team getTeamFromResultSet(ResultSet resultTeam) throws SQLException
    {
        Team team = null;
        if (resultTeam.next()) {
            team = new Team();
            team.setId_Team(resultTeam.getInt("Id_Team"));
            team.setId_Leader(resultTeam.getInt("Id_Leader"));
            team.setTeam_Name(resultTeam.getString("Team_Name"));

        }
        return team;
    }
}
