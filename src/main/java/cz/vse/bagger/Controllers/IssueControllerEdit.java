package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.IssueDao;
import cz.vse.bagger.Models.Issue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IssueControllerEdit {
    @FXML TextField issue;
    @FXML TextField issuePriority;
    @FXML TextArea issueDescription;
    @FXML Button issueEdit;
    private Issue wholeIssue;

    private MainPageController mainPageController;

    public void getIssue(Issue issue) {
        this.wholeIssue = issue;
    }

    public void issueEdit(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException, ParseException {
        SimpleDateFormat pom = new SimpleDateFormat("yyyyMMdd");
        Date xx = wholeIssue.getStart_Date();
        Date pom2 = pom.parse(String.valueOf(xx));
        Long result1 = Long.valueOf(pom.format(pom2));
        java.sql.Date startDate = new java.sql.Date(result1);

        try {
            Integer.parseInt(issuePriority.getText());
        } catch(NumberFormatException e){
            RootLayoutController.displayAlert("Wrong field format", "Priority must be numeric", "Field priority must be a number between 1 and 5.");
            return;
        }
        if ( Integer.parseInt(issuePriority.getText()) < 1 || Integer.parseInt(issuePriority.getText()) > 5 ){
            RootLayoutController.displayAlert("Wrong field format", "Priority must be numeric", "Field priority must be a number between 1 and 5.");
            return;
        }
        if (issue.getText().equals("") || issue.getText().trim().isEmpty() ){
            RootLayoutController.displayAlert("Wrong field format", "Name field must be filled", "Every issue must have a not empty name.");
            return;
        }

        IssueDao.updateIssue(wholeIssue.getId_Issue(),wholeIssue.getId_Project(),wholeIssue.getId_Creater(),issue.getText(),issueDescription.getText(),startDate,Integer.parseInt(issuePriority.getText()));
        RootLayoutController.giveConfirmation("Success", "Úspěšně jsi updatoval", "Úspěšně jsi updatoval záznam o chybě do databáze");
        Stage stage = (Stage) issueEdit.getScene().getWindow();
        stage.close();
        mainPageController.reload();
    }

    public void loadData() {
        issue.setText(wholeIssue.getIssue_Title());
        issuePriority.setText(String.valueOf(wholeIssue.getImportance()));
        issueDescription.setText(wholeIssue.getIssue_Description());
    }

    public void transferController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
}
