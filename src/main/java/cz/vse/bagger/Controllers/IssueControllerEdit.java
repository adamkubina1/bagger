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
    Issue wholeIssue;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        issue.setText(wholeIssue.getIssue_Title());
        issuePriority.setText(String.valueOf(wholeIssue.getImportance()));
        issueDescription.setText(wholeIssue.getIssue_Description());
    }

    public void getIssue(Issue issue) {
        this.wholeIssue = issue;
    }

    public void issueEdit(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException, ParseException {
        SimpleDateFormat pom = new SimpleDateFormat("yyyyMMdd");
        Date xx = wholeIssue.getStart_Date();
        Date yy = wholeIssue.getEnd_Date();
        Date pom2 = pom.parse(String.valueOf(xx));
        Long result1 = Long.valueOf(pom.format(pom2));
        java.sql.Date startDate = new java.sql.Date(result1);

        Date pom3 = pom.parse(String.valueOf(yy));
        Long result2 = Long.valueOf(pom.format(pom3));
        java.sql.Date endDate = new java.sql.Date(result2);

        IssueDao.updateIssue(wholeIssue.getId_Issue(),wholeIssue.getId_Project(),wholeIssue.getId_Creater(),wholeIssue.getId_Closer(),issue.getText(),issueDescription.getText(),startDate,endDate,Integer.parseInt(issuePriority.getText()));
        RootLayoutController.giveConfirmation("Success", "Úspěšně jsi updatoval", "Úspěšně jsi updatoval záznam o chybě do databáze");
        Stage stage = (Stage) issueEdit.getScene().getWindow();
        stage.close();
    }
}
