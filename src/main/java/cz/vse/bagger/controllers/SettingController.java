package cz.vse.bagger.controllers;

import cz.vse.bagger.dao.EmployeeDao;

import cz.vse.bagger.dao.LoginCredentialsDao;
import cz.vse.bagger.models.Employee;
import cz.vse.bagger.models.LoginCredentials;
import javafx.fxml.FXML;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *  Tato třída slouží jako ovládací kontroler k fxml oknu settings
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class SettingController {
    @FXML TextField userInfo;
    @FXML TextField teamLeader;
    @FXML TextField oldPassword;
    @FXML TextField newPassword;
    @FXML TextField confirmNewPassword;
    @FXML ListView<String> teamMembers;
    List<Employee> teamEmployees = new ArrayList<>();
    /**
     *  Tato metoda nastaví uživatelovi informace do textfieldů jako jeho jméno, je kolegy jeho teamleadera
     */
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        userInfo.setText(RootLayoutController.loggedEmployee.getName() + " " + RootLayoutController.loggedEmployee.getSurname());
        Employee leader = EmployeeDao.searchEmployee(RootLayoutController.loggedEmployeeTeam.getId_Leader());
        teamLeader.setText(leader.getName() + " " + leader.getSurname());
        teamEmployees.addAll(EmployeeDao.searchEmployeesByTeamId(RootLayoutController.loggedEmployeeTeam.getId_Team()));
        List<String> pom = new ArrayList<>();
        for (Employee employee : teamEmployees){
            pom.add(employee.getName() + " " + employee.getSurname());
        }
        teamMembers.getItems().addAll(pom);
    }
    /**
     *  Tato metoda slouží pro změnění hesla a vyhodí chybu když zadáte původní heslo špatně nebo nové heslo nebude 2x stejné
     */
    public void changePassword() throws SQLException, ClassNotFoundException {
        LoginCredentials login_credentials = LoginCredentialsDao.searchLoginCredentials(RootLayoutController.loggedEmployee.getId_Login_Credentials());
        if (!oldPassword.getText().equals(login_credentials.getPassword())) {
            RootLayoutController.displayAlert("Nesprávné heslo", "Zadal jsi nesprávné heslo", "Heslo které si zadal jako tvé původní heslo není stejné s tvým původním heslem");
        }
        else if (!newPassword.getText().equals(confirmNewPassword.getText())) {
            RootLayoutController.displayAlert("Nesprávné heslo", "Hesla se neshodují", "Nové heslo není shodné z potvrzením nového hesla");
        }
        else if (newPassword.getText().trim().isEmpty()){
            RootLayoutController.displayAlert("Nespravne heslo", "Prazdne heslo", "Heslo musi obsahovat nejake znaky.");
        }
        else {
            LoginCredentialsDao.updateLoginCredentialsPassword(login_credentials.getId_Login_Credentials(), newPassword.getText());
            RootLayoutController.displayConfirmation("Úspěch", "Heslo změněno", "Úspěšně jsi si změnil heslo na nové!");
        }
    }
}
