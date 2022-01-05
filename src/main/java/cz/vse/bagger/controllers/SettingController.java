package cz.vse.bagger.controllers;

import cz.vse.bagger.dao.EmployeeDao;

import cz.vse.bagger.dao.Login_CredentialsDAO;
import cz.vse.bagger.models.Employee;
import cz.vse.bagger.models.Login_Credentials;
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
    @FXML TextField user_Info;
    @FXML TextField team_Leader;
    @FXML TextField old_Password;
    @FXML TextField new_Password;
    @FXML TextField confirm_New_Password;
    @FXML ListView<String> team_Members;
    List<Employee> team_Employees = new ArrayList<>();
    /**
     *  Tato metoda nastaví uživatelovi informace do textfieldů jako jeho jméno, je kolegy jeho teamleadera
     */
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
    /**
     *  Tato metoda slouží pro změnění hesla a vyhodí chybu když zadáte původní heslo špatně nebo nové heslo nebude 2x stejné
     */
    public void changePassword() throws SQLException, ClassNotFoundException {
        Login_Credentials login_credentials = Login_CredentialsDAO.searchLoginCredentials(RootLayoutController.loggedEmployee.getId_Login_Credentials());
        if (!old_Password.getText().equals(login_credentials.getPassword())) {
            RootLayoutController.displayAlert("Nesprávné heslo", "Zadal jsi nesprávné heslo", "Heslo které si zadal jako tvé původní heslo není stejné s tvým původním heslem");
        }
        else if (!new_Password.getText().equals(confirm_New_Password.getText())) {
            RootLayoutController.displayAlert("Nesprávné heslo", "Hesla se neshodují", "Nové heslo není shodné z potvrzením nového hesla");
        }
        else if (new_Password.getText().trim().isEmpty()){
            RootLayoutController.displayAlert("Nespravne heslo", "Prazdne heslo", "Heslo musi obsahovat nejake znaky.");
        }
        else {
            Login_CredentialsDAO.updateLoginCredentialsPassword(login_credentials.getId_Login_Credentials(), new_Password.getText());
            RootLayoutController.displayConfirmation("Úspěch", "Heslo změněno", "Úspěšně jsi si změnil heslo na nové!");
        }
    }
}
