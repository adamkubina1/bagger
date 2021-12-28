package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.IssueDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 *  Tato třída slouží jako ovládací kontroler k fxml oknu newIssue
 */
public class IssueController {
    @FXML TextField newIssue;
    @FXML TextField newIssuePriority;
    @FXML TextArea newIssueDescription;
    @FXML Button newIssueButton;

    private int Id_Project;
    private MainPageController mainPageController;
    /**
     *  Tato metoda slouží k tomu aby se mohl přidat nový issue.
     *  Obsahuje try catch blok kódu pro validaci formátu.
     *  Volá metodu z insertIssue kterou zapíše nový issue do databáze.
     *  A reloadne issues tak aby se z databáze načetl seznam i s novým záznamem.
     */
    public void addNewIssue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        try {
            Integer.parseInt(newIssuePriority.getText());
        } catch(NumberFormatException e){
            RootLayoutController.displayAlert("Wrong field format", "Priority must be numeric", "Field priority must be a number between 1 and 5.");
            return;
        }
        if ( Integer.parseInt(newIssuePriority.getText()) < 1 || Integer.parseInt(newIssuePriority.getText()) > 5 ){
            RootLayoutController.displayAlert("Wrong field format", "Priority must be numeric", "Field priority must be a number between 1 and 5.");
            return;
        }
        if (newIssue.getText().equals("") || newIssue.getText().trim().isEmpty()){
            RootLayoutController.displayAlert("Wrong field format", "Name field must be filled", "Every issue must have a not empty name and priority.");
            return;
        }


        IssueDao.insertIssue(Id_Project, RootLayoutController.loggedEmployee.getId_Employee(), newIssue.getText(), newIssueDescription.getText(), date, Integer.parseInt(newIssuePriority.getText()));
        RootLayoutController.giveConfirmation("Success", "Úspěšně jsi uložil", "Úspěšně jsi uložil nový záznam do databáze");

        mainPageController.reloadIssues();
        Stage stage = (Stage) newIssueButton.getScene().getWindow();
        stage.close();
    }
    /**
     *  Tato metoda slouží k tomu aby se mohlo předat IdProject z jednoho Controlleru do druhého
     *
     *  @param Id_Project skrz tento parameter se předává id projektu z MainPageControlleru do CommentControlleru
     */
    public void getId_Project(int Id_Project) {
        this.Id_Project = Id_Project;
    }
    /**
     *  Tato metoda slouží k tomu aby se mohla předat instance mainPageControlleru z jednoho mainPageControlleru do issueControlleru
     *
     *  @param mainPageController skrz tento parameter se předává instance mainPageControlleru
     */
    public void transferController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }


}
