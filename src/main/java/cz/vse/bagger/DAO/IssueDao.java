package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Issue;
import cz.vse.bagger.Models.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueDao {
    //select, insert, update, delete
    public static Issue searchIssue (int Id_Issue) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Issue WHERE Id_Issue="+Id_Issue;

        try {
            ResultSet resultIssue = DBUtil.dbExecuteQuery(selectStmt);
            Issue issue = getIssueFromResultSet(resultIssue);
            return issue;
        } catch (SQLException exception) {
            System.out.println("While searching issue with " + Id_Issue + " id, an error occurred: " + exception);
            throw exception;
        }
    }

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


    private static Issue getIssueFromResultSet(ResultSet resultIssue) throws SQLException {
        Issue issue = null;
        if (resultIssue.next()) {
            issue = new Issue();
            issue.setId_Issue(resultIssue.getInt("Id_Issue"));
            issue.setId_Project(resultIssue.getInt("Id_Project"));
            issue.setId_Creater(resultIssue.getInt("Id_Creater"));
            issue.setIssue_Title(resultIssue.getString("Issue_Title"));
            issue.setIssue_Description(resultIssue.getString("Issue_Description"));
            issue.setStart_Date(resultIssue.getDate("Start_Date"));
            issue.setImportance(resultIssue.getInt("Importance"));
        }
        return issue;
    }
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

    public static ObservableList<Issue> searchProjects (int Id_Project) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Issue where Id_Project="+Id_Project;

        try {
            ResultSet resultIssues = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Issue> issueList = getIssueList(resultIssues);
            return issueList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }

    private static ObservableList<Issue> getIssueList(ResultSet resultIssue) throws SQLException, ClassNotFoundException {
        ObservableList<Issue> issueList = FXCollections.observableArrayList();
        while (resultIssue.next()) {
            Issue issue = new Issue();
            issue.setId_Issue(resultIssue.getInt("Id_Issue"));
            issue.setId_Project(resultIssue.getInt("Id_Project"));
            issue.setId_Creater(resultIssue.getInt("Id_Creater"));
            issue.setIssue_Title(resultIssue.getString("Issue_Title"));
            issue.setIssue_Description(resultIssue.getString("Issue_Description"));
            issue.setStart_Date(resultIssue.getDate("Start_Date"));
            issue.setImportance(resultIssue.getInt("Importance"));
            issueList.add(issue);
        }
        return issueList;
    }

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
