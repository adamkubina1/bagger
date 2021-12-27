package cz.vse.bagger.Controllers;

import javafx.scene.control.Alert;

public class RootLayoutController {

    public void displayAlert(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }

}
