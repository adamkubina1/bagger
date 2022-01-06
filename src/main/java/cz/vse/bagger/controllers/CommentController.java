package cz.vse.bagger.controllers;

import cz.vse.bagger.dao.CommentDao;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 *  Tato třída slouží jako ovládací kontroler k fxml oknu comment
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class CommentController {

    @FXML TextArea message;
    @FXML Button sendButton;

    private MainPageController mainPageController;
    private int idIssue;

    /**
     *  Tato metoda slouží k tomu aby se mohlo předat IdIssue z jednoho Controlleru do druhého
     *
     *  @param idIssue skrz tento parameter se předává id chyby z MainPageControlleru do CommentControlleru
     */
    public void transferIssueId(int idIssue) {
        this.idIssue = idIssue;
    }
    /**
     *  Tato metoda slouží k tomu aby se mohla předat instance mainPageControlleru z jednoho mainPageControlleru do commentControlleru
     *
     *  @param mainPageController skrz tento parameter se předává instance mainPageControlleru
     */
    public void transferController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    /**
     *  Tato metoda slouží k tomu aby se vložil nový komentář do databáze a reloadne komentáře aby se změna načetla.
     *
     */
    public void sendMessage() throws SQLException, ClassNotFoundException {
        CommentDao.insertComment(RootLayoutController.loggedEmployee.getIdEmployee(), idIssue, message.getText());

        mainPageController.reloadComments();

        Stage stage = (Stage) sendButton.getScene().getWindow();
        stage.close();
    }

}
