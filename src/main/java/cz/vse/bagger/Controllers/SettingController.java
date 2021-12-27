package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.EmployeeDao;

import cz.vse.bagger.Models.Employee;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingController {
    @FXML TextField user_Info;
    @FXML TextField team_Leader;
    @FXML ListView<String> team_Members;
    List<Employee> team_Employees = new ArrayList<>();

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        user_Info.setText(RootLayoutController.loggedEmployee.getName() + " " + RootLayoutController.loggedEmployee.getSurname());
        Employee leader = EmployeeDao.searchEmployee(RootLayoutController.loggedEmployeeTeam.getId_Leader());
        team_Leader.setText(leader.getName() + " " + leader.getSurname());
        team_Employees.addAll(EmployeeDao.searchEmployeesByTeamId(RootLayoutController.loggedEmployeeTeam.getId_Team()));
        List<String> pom = new ArrayList<>();
        for (Employee employee : team_Employees){
            pom.add(employee.getName() + " " + employee.getSurname());
        }
        team_Members.getItems().addAll(pom);
    }
}
