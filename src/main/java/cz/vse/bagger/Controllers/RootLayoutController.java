package cz.vse.bagger.Controllers;

import cz.vse.bagger.Models.Employee;
import cz.vse.bagger.Models.Team;
import javafx.scene.control.Alert;

public class RootLayoutController {
    public static Employee loggedEmployee; // Realy unsafe way of doing this
    public static Team loggedEmployeeTeam;

    public static void displayAlert(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }

    public static void displayConfirmation(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }

    public static Alert giveConfirmation(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        return alert;
    }
}
