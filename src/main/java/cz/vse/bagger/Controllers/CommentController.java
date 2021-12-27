package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.CommentDao;
import cz.vse.bagger.Models.Issue;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;


public class CommentController {

    @FXML ListView<Issue> issues;
    @FXML TextArea message;
    @FXML Button sendButton;
    int id_Issue;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        Issue selectedIssue = issues.getSelectionModel().getSelectedItem();
        id_Issue = selectedIssue.getId_Issue();


    }
    public void sendMessage() throws SQLException, ClassNotFoundException {
        CommentDao.insertComment(RootLayoutController.loggedEmployee.getId_Employee(),id_Issue, message.getText());
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

}
