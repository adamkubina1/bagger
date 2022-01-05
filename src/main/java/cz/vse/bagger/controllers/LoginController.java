package cz.vse.bagger.controllers;

import cz.vse.bagger.dao.EmployeeDao;
import cz.vse.bagger.dao.LoginCredentialsDao;
import cz.vse.bagger.models.Employee;
import cz.vse.bagger.models.LoginCredentials;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Objects;

/**
 *  Tato třída slouží jako ovládací kontroler k fxml oknu login
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class LoginController{
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button loginButton;


    /**
     *  Tato metoda slouží k validaci při přihlášení a přímo reaguje na kliknutí tlačítka login.
     */
    public void clickLogin(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String usernameText = username.getText();
        String passwordText = password.getText();

        // One or both of the fields are blank
        if (( usernameText.equals("") || passwordText.equals(""))){
            RootLayoutController.displayAlert("Failed to login", "Missing field", "Please enter your login credentials into all required fields.");
        }
        else {

            LoginCredentials login_credentials = LoginCredentialsDao.login(usernameText, passwordText);

            // The validation of user credentials failed
            if(Objects.isNull(login_credentials)){
                RootLayoutController.displayAlert("Failed to login", "Wrong username of password", "Try to check if your login credentials are typed in correctly");
                username.clear();
                password.clear();
            }

            else {
                succesfullLogin(login_credentials);
            }
        }
    }
    /**
     *  Tato metoda slouží k otevření hlavního okna po úspěšném příhlášení a také uloží údaje o uživateli do statických proměných.
     */
    private void succesfullLogin(LoginCredentials validLoginCredentials) throws SQLException, ClassNotFoundException {
        Employee loggedEmployee = EmployeeDao.searchEmployee(validLoginCredentials.getId_Login_Credentials());
        RootLayoutController.loggedEmployee = loggedEmployee; // This is super unsafe way of doing this

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.close();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
