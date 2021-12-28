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

    private int Id_Project;
    private MainPageController mainPageController;

    public void addNewIssue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        java.sql.Date endDate = new java.sql.Date(00);//FUCKING HEELLLL


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


        IssueDao.insertIssue(Id_Project, RootLayoutController.loggedEmployee.getId_Employee(), RootLayoutController.loggedEmployee.getId_Employee(), newIssue.getText(), newIssueDescription.getText(), date,endDate, Integer.parseInt(newIssuePriority.getText()));//FUCKING HELLL  OTVIRAME ZAVIRAME ROVKOUUUUUUUUUUUU
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
