package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.EmployeeDao;

import cz.vse.bagger.Models.Employee;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class SettingController {
    @FXML TextField user_Info;
    @FXML TextField team_Leader;
    @FXML ListView<Employee> team_Members;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        user_Info.setText(RootLayoutController.loggedEmployee.getName() + " " + RootLayoutController.loggedEmployee.getSurname());
        Employee leader = EmployeeDao.searchEmployee(RootLayoutController.loggedEmployeeTeam.getId_Leader());
        team_Leader.setText(leader.getName() + " " + leader.getSurname());
        team_Members.getItems().addAll(EmployeeDao.searchEmployeesByTeamId(RootLayoutController.loggedEmployeeTeam.getId_Team()));
    }

}
