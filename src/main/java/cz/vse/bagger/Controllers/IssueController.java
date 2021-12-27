package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.IssueDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

public class IssueController {
    @FXML TextField newIssue;
    @FXML TextField newIssuePriority;
    @FXML TextArea newIssueDescription;
    @FXML Button newIssueButton;
    int Id_Project;
    int Id_Issue;


    public void addNewIssue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Calendar calendar = Calendar.getInstance();
        Date date = (Date) calendar.getTime();
        int newIssuePriorityInt = Integer.parseInt(newIssueDescription.getText());
        IssueDao.insertIssue(Id_Issue, Id_Project, RootLayoutController.loggedEmployee.getId_Employee(), 0, newIssue.getText(), newIssueDescription.getText(), date,null, newIssuePriorityInt);
        RootLayoutController.giveConfirmation("Success", "Úspěšně jsi uložil", "Úspěšně jsi uložil nový záznam do databáze");
    }

    public void getId_Project(int Id_Project, int Id_Issue) {
        this.Id_Project = Id_Project;
        this.Id_Issue = Id_Issue;
    }
}
