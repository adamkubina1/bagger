package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.ProjectDao;
import cz.vse.bagger.DAO.TeamDao;
import cz.vse.bagger.Models.Project;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;



public class MainPageController {
    @FXML Label userLabel;
    @FXML Label teamLabel;
    @FXML ListView<Project> projects;


    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        System.out.println(RootLayoutController.loggedEmployee.getId_Team());
        RootLayoutController.loggedEmployeeTeam = TeamDao.searchTeam(String.valueOf(RootLayoutController.loggedEmployee.getId_Team()));

        userLabel.setText(RootLayoutController.loggedEmployee.getName() + " " + RootLayoutController.loggedEmployee.getSurname());
        teamLabel.setText(RootLayoutController.loggedEmployeeTeam.getTeam_Name());

        projects.getItems().addAll(ProjectDao.searchProjects(String.valueOf(RootLayoutController.loggedEmployeeTeam.getId_Team())));

    }





}
