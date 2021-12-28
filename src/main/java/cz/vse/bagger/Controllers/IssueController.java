package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.IssueDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;


public class IssueController {
    @FXML TextField newIssue;
    @FXML TextField newIssuePriority;
    @FXML TextArea newIssueDescription;
    @FXML Button newIssueButton;
    int Id_Project;

    private MainPageController mainPageController;

    public void addNewIssue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        int newIssuePriorityInt = Integer.parseInt(newIssuePriority.getText());
        java.sql.Date endDate = new java.sql.Date(00);//FUCKING HEELLLL
        IssueDao.insertIssue(Id_Project, RootLayoutController.loggedEmployee.getId_Employee(), RootLayoutController.loggedEmployee.getId_Employee(), newIssue.getText(), newIssueDescription.getText(), date,endDate, newIssuePriorityInt);//FUCKING HELLL  OTVIRAME ZAVIRAME ROVKOUUUUUUUUUUUU
        RootLayoutController.giveConfirmation("Success", "Úspěšně jsi uložil", "Úspěšně jsi uložil nový záznam do databáze");
        Stage stage = (Stage) newIssueButton.getScene().getWindow();
        stage.close();
        mainPageController.reload();
    }

    public void getId_Project(int Id_Project) {
        this.Id_Project = Id_Project;
    }

    public void transferController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }


}
