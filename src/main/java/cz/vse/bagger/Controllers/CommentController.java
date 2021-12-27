package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.CommentDao;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;


public class CommentController {

    @FXML TextArea message;
    @FXML Button sendButton;
    int id_Issue;

    public void transferIssueId(int id_Issue) {
        this.id_Issue = id_Issue;
    }

    public void sendMessage() throws SQLException, ClassNotFoundException {
        CommentDao.insertComment(RootLayoutController.loggedEmployee.getId_Employee(),id_Issue, message.getText());
        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

}
