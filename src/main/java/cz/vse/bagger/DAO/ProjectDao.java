package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDao {
    public static Project searchProject (int Id_Project) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Project WHERE Id_Project="+Id_Project;

        try {
            ResultSet resultProject = DBUtil.dbExecuteQuery(selectStmt);
            Project project = getProjectFromResultSet(resultProject);
            return project;
        } catch (SQLException exception) {
            System.out.println("While searching project with " + Id_Project + " id, an error occurred: " + exception);
            throw exception;
        }
    }
    private static Project getProjectFromResultSet(ResultSet resultProject) throws SQLException
    {
        Project project = null;
        if (resultProject.next()) {
            project = new Project();
            project.setId_Project(resultProject.getInt("Id_Project"));
            project.setProject_Name(resultProject.getString("Project_Name"));
        }
        return project;
    }

    public static ObservableList<Project> searchProjects () throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Projects";

        try {
            ResultSet resultProjects = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Project> projectList = getProjectList(resultProjects);
            return projectList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }

    private static ObservableList<Project> getProjectList(ResultSet resultProjects) throws SQLException, ClassNotFoundException {
        ObservableList<Project> projectList = FXCollections.observableArrayList();
        while (resultProjects.next()) {
            Project project = new Project();
            project.setId_Project(resultProjects.getInt("Id_Project"));
            project.setProject_Name(resultProjects.getString("Project_Name"));
            projectList.add(project);
        }
        return projectList;
    }

    public static void updateProject (int Id_Project, String Project_Name) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE Project\n" +
                        "      SET Project_Name = '" + Project_Name + "'\n" +
                        "    WHERE Id_Project = " + Id_Project + ";\n" +
                        "   COMMIT;\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        }
        catch (SQLException exception) {
            System.out.print("Error occurred while UPDATE Operation: " + exception);
            throw exception;
        }
    }

    public static void insertProject (String Project_Name) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO Project\n" +
                        "(Project_Name)\n" +
                        "VALUES\n" +
                        "('"+Project_Name+"');\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }

    public static void deleteProjectWithId (int Id_Project) throws SQLException, ClassNotFoundException {
        String deleteStmt =
                "BEGIN\n" +
                        "   DELETE FROM Project\n" +
                        "         WHERE Id_Project ="+ Id_Project +";\n" +
                        "   COMMIT;\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(deleteStmt);
        } catch (SQLException exception) {
            System.out.print("Error occurred while DELETE Operation: " + exception);
            throw exception;
        }
    }
}