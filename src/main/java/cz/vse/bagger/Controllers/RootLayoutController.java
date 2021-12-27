package cz.vse.bagger.Controllers;

import cz.vse.bagger.Models.Employee;
import javafx.scene.control.Alert;

public class RootLayoutController {
    public static Employee loggedEmployee; // Realy unsafe way of doing this

    public static void displayAlert(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }

}
