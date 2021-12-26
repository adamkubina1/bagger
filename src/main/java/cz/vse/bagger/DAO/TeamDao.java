package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Team;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamDao {
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
    private static Team getTeamFromResultSet(ResultSet resultTeam) throws SQLException
    {
        Team team = null;
        if (resultTeam.next()) {
            team = new Team();
            team.setId_Team(resultTeam.getInt("Id_Team"));
            team.setId_Leader(resultTeam.getInt("Id_Leader"));
            team.setTeam_Name(resultTeam.getString("Name"));

        }
        return team;
    }
}
