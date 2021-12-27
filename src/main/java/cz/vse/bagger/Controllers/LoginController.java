package cz.vse.bagger.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class LoginController{
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button loginButton;



    public void clickLogin(MouseEvent mouseEvent){
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (( usernameText.equals("") || passwordText.equals(""))){
            RootLayoutController rootLayoutController = new RootLayoutController();

            rootLayoutController.displayAlert("Failed to login", "Missing field", "Please enter your login credentials into all required fields.");
        } else {

            succesfullLogin();
        }
    }

    private void succesfullLogin(){
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
