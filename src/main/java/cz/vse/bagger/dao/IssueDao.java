package cz.vse.bagger.dao;

import cz.vse.bagger.models.Issue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Issue
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class IssueDao {

    /**
     *  Tato metoda vyhledá v databázi Issues patřící pod konkrétní projekt
     *  @param idProject Id projektu říká pod který projekt dané issues patří
     */
    public static ObservableList<Issue> searchIssueOnProject (int idProject) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Issue WHERE Id_Project="+idProject;

        try {
            ResultSet resultIssue = DBUtil.dbExecuteQuery(SELECT_STMT);
            ObservableList<Issue> issues = getIssueListFromResultSet(resultIssue);
            return issues;
        } catch (SQLException exception) {
            System.out.println("While searching issue with " + idProject + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda zpracovává výsledek ze searchIssueOnProject metody a záznamy které ta metoda vrátila dává do Listu.
     *  @param resultIssues je výsledek který vrátila databáze na naše querry.
     */
    private static ObservableList<Issue> getIssueListFromResultSet(ResultSet resultIssues) throws SQLException {
        ObservableList<Issue> issuesList = FXCollections.observableArrayList();
        while (resultIssues.next()) {
            Issue issue = new Issue();
            issue.setIdIssue(resultIssues.getInt("Id_Issue"));
            issue.setIdProject(resultIssues.getInt("Id_Project"));
            issue.setIdCreater(resultIssues.getInt("Id_Creater"));
            issue.setIssueTitle(resultIssues.getString("Issue_Title"));
            issue.setIssueDescription(resultIssues.getString("Issue_Description"));
            issue.setStartDate(resultIssues.getDate("Start_Date"));
            issue.setImportance(resultIssues.getInt("Importance"));
            issuesList.add(issue);
        }
        return issuesList;
    }
    /**
     *  Tato metoda ukládá nový komentář do databáze.
     *  @param idIssue je Id issue které chceme updatnout
     *  @param idProject je Id projektu ke kterému to issue patří
     *  @param idCreater Id zaměstnance který vytvořil issue
     *  @param issueTitle název issue
     *  @param issueDescription zpráva kterou ukládáme k danému issue
     *  @param startDate datum vytvoření issue
     *  @param importance říká jak důležitý je daný issue
     */
    public static void updateIssue (int idIssue, int idProject, int idCreater, String issueTitle, String issueDescription, Date startDate, int importance) throws SQLException, ClassNotFoundException {
        final String UPDATE_STMT =
                        "   UPDATE Issue\n" +
                        "      SET Id_Project = '" + idProject + "'\n" +
                        "      , Id_Creater = '" + idCreater + "'\n" +
                        "      , Issue_Title = '" + issueTitle + "'\n" +
                        "      , Issue_Description = '" + issueDescription + "'\n" +
                        "      , Start_Date = '" + startDate + "'\n" +
                        "      , Importance = '" + importance + "'\n" +
                        "    WHERE Id_Issue = " + idIssue + ";";
        try {
            DBUtil.dbExecuteUpdate(UPDATE_STMT);
        }
        catch (SQLException exception) {
            System.out.print("Error occurred while UPDATE Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda ukládá nový issue do databáze.
     *  @param idProject je Id projektu ke kterému to issue patří
     *  @param idCreater Id zaměstnance který vytvořil issue
     *  @param issueTitle název issue
     *  @param issueDescription zpráva kterou ukládáme k danému issue
     *  @param startDate datum vytvoření issue
     *  @param importance říká jak důležitý je daný issue
     */
    public static void insertIssue (int idProject, int idCreater, String issueTitle, String issueDescription, Date startDate, int importance) throws SQLException, ClassNotFoundException {
        final String UPDATE_STMT =
                        "INSERT INTO Issue\n" +
                        "(Id_Project, Id_Creater, Issue_Title, Issue_Description, Start_Date, Importance)\n" +
                        "VALUES\n" +
                        "('"+idProject+"', '"+idCreater+"', '"+issueTitle+"','"+issueDescription+"', '"+startDate+"', '"+importance+"');";

        try {
            DBUtil.dbExecuteUpdate(UPDATE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda smaže v databázi Issue s daným Id
     *  @param idIssue Id Issue které chceme vymazat
     */
    public static void deleteIssueWithId (int idIssue) throws SQLException, ClassNotFoundException {
        final String DELETE_STMT =
                        "   DELETE FROM Issue\n" +
                        "         WHERE Id_Issue ="+ idIssue +";";
        try {
            DBUtil.dbExecuteUpdate(DELETE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while DELETE Operation: " + exception);
            throw exception;
        }
    }
}
