package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.Login_CredentialsDAO;
import cz.vse.bagger.Models.Login_Credentials;
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


public class LoginController{

    private RootLayoutController rootLayoutController = new RootLayoutController();

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button loginButton;



    public void clickLogin(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String usernameText = username.getText();
        String passwordText = password.getText();

        // One or both of the fields are blank
        if (( usernameText.equals("") || passwordText.equals(""))){
            rootLayoutController.displayAlert("Failed to login", "Missing field", "Please enter your login credentials into all required fields.");
        }
        else {

            Login_Credentials login_credentials = Login_CredentialsDAO.login(usernameText, passwordText);

            // The validation of user credentials failed
            if(Objects.isNull(login_credentials)){
                rootLayoutController.displayAlert("Failed to login", "Wrong username of password", "Try to check if your login credentials are typed in correctly");
                username.clear();
                password.clear();
            }

            else {
                succesfullLogin(login_credentials);
            }
        }
    }

    private void succesfullLogin(Login_Credentials validLoginCredentials){
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
