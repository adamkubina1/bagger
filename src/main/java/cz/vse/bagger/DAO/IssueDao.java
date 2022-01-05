package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Issue;
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
     *  @param Id_Project Id projektu říká pod který projekt dané issues patří
     */
    public static ObservableList<Issue> searchIssueOnProject (int Id_Project) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Issue WHERE Id_Project="+Id_Project;

        try {
            ResultSet resultIssue = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Issue> issues = getIssueListFromResultSet(resultIssue);
            return issues;
        } catch (SQLException exception) {
            System.out.println("While searching issue with " + Id_Project + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda zpracovává výsledek ze searchIssueOnProject metody a záznamy které ta metoda vrátila dává do Listu.
     *  @param resultIssues je výsledek který vrátila databáze na naše querry.
     */
    private static ObservableList<Issue> getIssueListFromResultSet(ResultSet resultIssues) throws SQLException, ClassNotFoundException {
        ObservableList<Issue> issuesList = FXCollections.observableArrayList();
        while (resultIssues.next()) {
            Issue issue = new Issue();
            issue.setId_Issue(resultIssues.getInt("Id_Issue"));
            issue.setId_Project(resultIssues.getInt("Id_Project"));
            issue.setId_Creater(resultIssues.getInt("Id_Creater"));
            issue.setIssue_Title(resultIssues.getString("Issue_Title"));
            issue.setIssue_Description(resultIssues.getString("Issue_Description"));
            issue.setStart_Date(resultIssues.getDate("Start_Date"));
            issue.setImportance(resultIssues.getInt("Importance"));
            issuesList.add(issue);
        }
        return issuesList;
    }
    /**
     *  Tato metoda ukládá nový komentář do databáze.
     *  @param Id_Issue je Id issue které chceme updatnout
     *  @param Id_Project je Id projektu ke kterému to issue patří
     *  @param Id_Creater Id zaměstnance který vytvořil issue
     *  @param Issue_Title název issue
     *  @param Issue_Description zpráva kterou ukládáme k danému issue
     *  @param Start_Date datum vytvoření issue
     *  @param Importance říká jak důležitý je daný issue
     */
    public static void updateIssue (int Id_Issue, int Id_Project, int Id_Creater, String Issue_Title, String Issue_Description, Date Start_Date, int Importance) throws SQLException, ClassNotFoundException {
        String updateStmt =
                        "   UPDATE Issue\n" +
                        "      SET Id_Project = '" + Id_Project + "'\n" +
                        "      , Id_Creater = '" + Id_Creater + "'\n" +
                        "      , Issue_Title = '" + Issue_Title + "'\n" +
                        "      , Issue_Description = '" + Issue_Description + "'\n" +
                        "      , Start_Date = '" + Start_Date + "'\n" +
                        "      , Importance = '" + Importance + "'\n" +
                        "    WHERE Id_Issue = " + Id_Issue + ";";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        }
        catch (SQLException exception) {
            System.out.print("Error occurred while UPDATE Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda ukládá nový issue do databáze.
     *  @param Id_Project je Id projektu ke kterému to issue patří
     *  @param Id_Creater Id zaměstnance který vytvořil issue
     *  @param Issue_Title název issue
     *  @param Issue_Description zpráva kterou ukládáme k danému issue
     *  @param Start_Date datum vytvoření issue
     *  @param Importance říká jak důležitý je daný issue
     */
    public static void insertIssue (int Id_Project, int Id_Creater, String Issue_Title, String Issue_Description, Date Start_Date, int Importance) throws SQLException, ClassNotFoundException {
        String updateStmt =
                        "INSERT INTO Issue\n" +
                        "(Id_Project, Id_Creater, Issue_Title, Issue_Description, Start_Date, Importance)\n" +
                        "VALUES\n" +
                        "('"+Id_Project+"', '"+Id_Creater+"', '"+Issue_Title+"','"+Issue_Description+"', '"+Start_Date+"', '"+Importance+"');";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda smaže v databázi Issue s daným Id
     *  @param Id_Issue Id Issue které chceme vymazat
     */
    public static void deleteIssueWithId (int Id_Issue) throws SQLException, ClassNotFoundException {
        String deleteStmt =
                        "   DELETE FROM Issue\n" +
                        "         WHERE Id_Issue ="+ Id_Issue +";";
        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException exception) {
            System.out.print("Error occurred while DELETE Operation: " + exception);
            throw exception;
        }
    }
}
