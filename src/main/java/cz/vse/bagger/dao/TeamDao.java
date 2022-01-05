package cz.vse.bagger.dao;

import cz.vse.bagger.models.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Team
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class TeamDao {

    /**
     *  Tato metoda vyhledá v databázi Team který má dané team Id
     *  @param idTeam Id říká jaký tým chceme vyhledat
     */
    public static Team searchTeam (String idTeam) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Team WHERE Id_Team="+idTeam;

        try {
            ResultSet resultTeam = DBUtil.dbExecuteQuery(SELECT_STMT);
            Team team = getTeamFromResultSet(resultTeam);
            return team;
        } catch (SQLException exception) {
            System.out.println("While searching team with " + idTeam + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda vyhledá v databázi Team podle jeho vedoucího
     *  @param idLeader říká jaké id má daný leader abychom vyhledaly záznam
     */
    public static Team searchTeamOnLeader (String idLeader) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Team WHERE Id_Leader="+idLeader;

        try {
            ResultSet resultTeam = DBUtil.dbExecuteQuery(SELECT_STMT);
            Team team = getTeamFromResultSet(resultTeam);
            return team;
        } catch (SQLException exception) {
            System.out.println("While searching team with " + idLeader + " id, an error occurred: " + exception);
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
            team.setIdTeam(resultTeam.getInt("Id_Team"));
            team.setIdLeader(resultTeam.getInt("Id_Leader"));
            team.setTeamName(resultTeam.getString("Team_Name"));

        }
        return team;
    }
}
