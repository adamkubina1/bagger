package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.EmployeeDao;

import cz.vse.bagger.DAO.Login_CredentialsDAO;
import cz.vse.bagger.Models.Employee;
import cz.vse.bagger.Models.Login_Credentials;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingController {
    @FXML TextField user_Info;
    @FXML TextField team_Leader;
    @FXML TextField old_Password;
    @FXML TextField new_Password;
    @FXML TextField confirm_New_Password;
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

    public void changePassword() throws SQLException, ClassNotFoundException {
        Login_Credentials login_credentials = Login_CredentialsDAO.searchLoginCredentials(RootLayoutController.loggedEmployee.getId_Login_Credentials());
        if (!old_Password.getText().equals(login_credentials.getPassword())) {
            RootLayoutController.displayAlert("Nesprávné heslo", "Zadal jsi nesprávné heslo", "Heslo které si zadal jako tvé původní heslo není stejné s tvým původním heslem");
        }
        else if (!new_Password.getText().equals(confirm_New_Password.getText())) {
            RootLayoutController.displayAlert("Nesprávné heslo", "Hesla se neshodují", "Nové heslo není shodné z potvrzením nového hesla");
        }
        else {
            Login_CredentialsDAO.updateLoginCredentialsPassword(login_credentials.getId_Login_Credentials(), new_Password.getText());
        }
    }
}
