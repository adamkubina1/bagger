package cz.vse.bagger.dao;

import cz.vse.bagger.models.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Project
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class ProjectDao {
    
    /**
     *  Tato metoda vyhledá v databázi Projekt podle jeho jména
     *  @param projectName říká jméno týmu podle kterého hledáme
     */
    public static Project searchProject (String projectName) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Project WHERE Project_Name="+"'"+projectName+"'";

        try {
            ResultSet resultProject = DBUtil.dbExecuteQuery(SELECT_STMT);
            Project project = getProjectFromString(resultProject);
            return project;
        } catch (SQLException exception) {
            System.out.println("While searching project with " + projectName + " name, an error occurred: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda zpracovává výsledek ze login searchProject a záznamy které ta metoda vrátila dává do proměnné typu Project.
     *  @param resultProject je výsledek který vrátila databáze na naše querry.
     */
    private static Project getProjectFromString(ResultSet resultProject) throws SQLException
    {
        Project project = null;
        if (resultProject.next()) {
            project = new Project();
            project.setId_Project(resultProject.getInt("Id_Project"));
            project.setProject_Name(resultProject.getString("Project_Name"));
        }
        return project;
    }
    /**
     *  Tato metoda vyhledá v databázi Projekt podle team Id
     *  @param teamID říká id týmu podle kterého hledáme
     */
    public static ObservableList<Project> searchProjects (int teamID) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT =  "select Project.Id_Project, Project.Project_Name from Project inner join Team_Project_Relationship \n" +
                "on Project.Id_Project = Team_Project_Relationship.Id_Project \n" +
                "where Team_Project_Relationship.Id_Team =" + teamID;

        try {
            ResultSet resultProjects = DBUtil.dbExecuteQuery(SELECT_STMT);
            ObservableList<Project> projectList = getProjectList(resultProjects);
            return projectList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda vyhledá v databázi projekty které uživatel nemá přiřazené ke svému týmu
     *  @param teamID říká jméno týmu ke kterému nemají být přiřazené projekty
     */
    public static ObservableList<Project> searchNotUsedProjects (int teamID) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT =  "select Project.Id_Project, Project.Project_Name from Project join Team_Project_Relationship\n" +
                "on Project.Id_Project = Team_Project_Relationship.Id_Project \n" +
                "where Project.Id_Project not in (select Project.Id_Project from Project join Team_Project_Relationship\n" +
                "on Project.Id_Project = Team_Project_Relationship.Id_Project where Team_Project_Relationship.Id_Team ="+ teamID +" );";

        try {
            ResultSet resultProjects = DBUtil.dbExecuteQuery(SELECT_STMT);
            ObservableList<Project> projectList = getProjectList(resultProjects);
            return projectList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda zpracovává výsledek ze searchProjects a searchNotUsedProjects metod záznamy které ta metoda vrátila dává do Listu.
     *  @param resultProjects je výsledek který vrátila databáze na naše querry.
     */
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
    /**
     *  Tato metoda ukládá nový projekt a dává mu název
     *  @param projectName je název nového projektu
     */
    public static void insertProject (String projectName) throws SQLException, ClassNotFoundException {
        final String UPDATE_STMT =
                "INSERT INTO Project\n" +
                        "(Project_Name)\n" +
                        "VALUES\n" +
                        "('"+projectName+"')";

        try {
            DBUtil.dbExecuteUpdate(UPDATE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda přiřazuje týmu projekty
     *  @param idTeam tým kterému chceme přiřadit projekt
     *  @param idProject projekt který chceme přiřadit
     */
    public static void insertTeam_projectRelationship (int idTeam, int idProject) throws SQLException, ClassNotFoundException {
        final String UPDATE_STMT =
                "INSERT INTO Team_Project_Relationship\n" +
                        "(Id_Team, Id_Project)\n" +
                        "VALUES\n" +
                        "('"+idTeam+"','"+idProject+"');";

        try {
            DBUtil.dbExecuteUpdate(UPDATE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda přiřazuje týmu projekty
     *  @param idProject projekt který chceme přiřadit
     *  @param idTeam tým kterému chceme přiřadit projekt
     */
    public static void insertTeamToProject (int idProject, int idTeam ) throws SQLException, ClassNotFoundException {
        final String UPDATE_STMT = "INSERT INTO Team_Project_Relationship (Id_Team, Id_Project) VALUES ('"+idTeam+"', '"+idProject+"');";


        try {
            DBUtil.dbExecuteUpdate(UPDATE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
    /**
     *  Tato metoda maže projekt na základě jeho Id
     *  @param idProject id projektu který chceme vymazat
     */
    public static void deleteProjectWithId (int idProject) throws SQLException, ClassNotFoundException {
        final String DELETE_STMT =
                        "   DELETE FROM Project\n" +
                        "         WHERE Id_Project ="+ idProject +";";
        try {
            DBUtil.dbExecuteUpdate(DELETE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while DELETE Operation: " + exception);
            throw exception;
        }
    }
}
