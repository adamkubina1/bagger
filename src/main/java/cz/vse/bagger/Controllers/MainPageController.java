package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.CommentDao;
import cz.vse.bagger.DAO.IssueDao;
import cz.vse.bagger.DAO.ProjectDao;
import cz.vse.bagger.DAO.TeamDao;
import cz.vse.bagger.Models.Comment;
import cz.vse.bagger.Models.Issue;
import cz.vse.bagger.Models.Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;


public class MainPageController {
    public ImageView deleteProject;
    @FXML Label userLabel;
    @FXML Label teamLabel;
    @FXML ListView<Project> projects;
    @FXML ListView<Issue> issues;
    @FXML ListView<Comment> comments;
    @FXML TextField issueName;
    @FXML TextField issuePriority;
    @FXML TextArea issueDescription;
    @FXML Button newProject;
    @FXML Button newComment;
    @FXML Button newIssue;
    @FXML Button editIssue;
    @FXML Button closeIssue;


    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {

        RootLayoutController.loggedEmployeeTeam = TeamDao.searchTeam(String.valueOf(RootLayoutController.loggedEmployee.getId_Team()));

        userLabel.setText(RootLayoutController.loggedEmployee.getName() + " " + RootLayoutController.loggedEmployee.getSurname());
        teamLabel.setText(RootLayoutController.loggedEmployeeTeam.getTeam_Name());

        projects.getItems().addAll(ProjectDao.searchProjects(RootLayoutController.loggedEmployeeTeam.getId_Team()));

        newComment.setDisable(true);
        newIssue.setDisable(true);
        editIssue.setDisable(true);
        closeIssue.setDisable(true);

        if(Objects.isNull(TeamDao.searchTeamOnLeader(String.valueOf(RootLayoutController.loggedEmployee.getId_Employee())))){
            newProject.setDisable(true);
            deleteProject.setDisable(true);
        }
    }


    public void selectedProject(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Project selectedProject = projects.getSelectionModel().getSelectedItem();

        if(Objects.isNull(selectedProject))return;

        issues.getItems().clear();
        comments.getItems().clear();
        issueName.clear();
        issuePriority.clear();
        issueDescription.clear();

        newComment.setDisable(true);
        newIssue.setDisable(false);
        editIssue.setDisable(true);
        closeIssue.setDisable(true);

        issues.getItems().addAll(IssueDao.searchIssueOnProject(selectedProject.getId_Project()));
    }


    public void selectedIssue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Issue selectedIssue = issues.getSelectionModel().getSelectedItem();

        if(Objects.isNull(selectedIssue))return;

        comments.getItems().clear();
        issueName.clear();
        issuePriority.clear();
        issueDescription.clear();

        comments.getItems().addAll(CommentDao.searchComments(selectedIssue.getId_Issue()));

        newComment.setDisable(false);
        newIssue.setDisable(false);
        editIssue.setDisable(false);
        closeIssue.setDisable(false);

        issueName.setText(selectedIssue.getIssue_Title());
        issuePriority.setText(String.valueOf(selectedIssue.getImportance()));
        issueDescription.setText(selectedIssue.getIssue_Description());
    }

    public void newProject(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/newProject.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();

        ProjectController projectController = loader.getController();
        projectController.transferController(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newIssue(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/newIssue.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();

        Project selectedProject = projects.getSelectionModel().getSelectedItem();
        IssueController issueController = loader.getController();
        issueController.getId_Project(selectedProject.getId_Project());
        issueController.transferController(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newComment(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/comment.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();

        Issue selectedIssue = issues.getSelectionModel().getSelectedItem();
        CommentController commentController = loader.getController();
        commentController.transferIssueId(selectedIssue.getId_Issue());
        commentController.transferController(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void editIssue(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editIssue.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();

        Issue selectedIssue = issues.getSelectionModel().getSelectedItem();
        IssueControllerEdit issueControllerEdit = loader.getController();
        issueControllerEdit.getIssue(selectedIssue);
        issueControllerEdit.loadData();
        issueControllerEdit.transferController(this);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void openSettings(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/settings.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = new Stage();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void reload() throws SQLException, ClassNotFoundException {
        projects.getItems().clear();
        issues.getItems().clear();
        comments.getItems().clear();
        issueName.clear();
        issuePriority.clear();
        issueDescription.clear();

        projects.getItems().addAll(ProjectDao.searchProjects(RootLayoutController.loggedEmployeeTeam.getId_Team()));

        newComment.setDisable(true);
        newIssue.setDisable(true);
        editIssue.setDisable(true);
        closeIssue.setDisable(true);
    }

    public void reloadIssues() throws SQLException, ClassNotFoundException {
        issues.getItems().clear();
        issueName.clear();
        issuePriority.clear();
        issueDescription.clear();

        Project selectedProject = projects.getSelectionModel().getSelectedItem();

        issues.getItems().addAll(IssueDao.searchIssueOnProject(selectedProject.getId_Project()));

        newComment.setDisable(true);
        editIssue.setDisable(true);
        closeIssue.setDisable(true);
    }

    public void reloadComments() throws SQLException, ClassNotFoundException {
        Issue selectedIssue = issues.getSelectionModel().getSelectedItem();
        comments.getItems().clear();

        comments.getItems().addAll(CommentDao.searchComments(selectedIssue.getId_Issue()));
    }

    public void closeIssue(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Alert conformation = RootLayoutController.giveConfirmation("Close issue", "Permanent issue removal", "You are about to permanently remove this issue, are you sure?");

        Optional<ButtonType> result = conformation.showAndWait();
        if (result.get() == ButtonType.OK){
            Issue selectedIssue = issues.getSelectionModel().getSelectedItem();

            if(Objects.isNull(selectedIssue)){ // This is not necesary but ensuring safety, button should be disabled in unsafe situations
                RootLayoutController.displayAlert("Delete error", "No issue selected", "Please select issue for deletion.");
            } else {

                IssueDao.deleteIssueWithId(selectedIssue.getId_Issue());

                reload();
            }

        }
    }

    public void deleteProject(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Alert conformation = RootLayoutController.giveConfirmation("Delete project", "Permanent permanent removal", "You are about to permanently delete this project, are you sure?");

        Optional<ButtonType> result = conformation.showAndWait();
        if (result.get() == ButtonType.OK){
            Project selectedProject = projects.getSelectionModel().getSelectedItem();

            if(Objects.isNull(selectedProject)){
                RootLayoutController.displayAlert("Delete error", "No project selected", "Please select project for deletion.");
            } else {
                ProjectDao.deleteProjectWithId(selectedProject.getId_Project());

                reload();
            }


        }
    }
}
