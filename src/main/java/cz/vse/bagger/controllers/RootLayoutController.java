package cz.vse.bagger.controllers;

import cz.vse.bagger.models.Employee;
import cz.vse.bagger.models.Team;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

/**
 *  Tato třída pro uchování statických proměných uživatele a pro vyvolání alertů
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class RootLayoutController {
    public static Employee loggedEmployee; // Realy unsafe way of doing this
    public static Team loggedEmployeeTeam;
    /**
     *  Tato metoda slouží zobrazení alertu, že jsme třeba špatně vyplnily heslo atd...
     */
    public static void displayAlert(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();

        dialogPane.setId("alert");
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }
    /**
     *  Tato metoda slouží zobrazení alertu, že se něco stalo jako uložení atd...
     */
    public static void displayConfirmation(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();

        dialogPane.setId("confirmation");
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }
    /**
     *  Tato metoda slouží pro dotázaní jestli nějakou akci opravdu chcete udělat
     */
    public static Alert giveConfirmation(String title, String header, String description){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        DialogPane dialogPane = alert.getDialogPane();

        dialogPane.setId("confirmation");
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(description);

        return alert;
    }
}
