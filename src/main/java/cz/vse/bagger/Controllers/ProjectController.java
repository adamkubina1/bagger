package cz.vse.bagger.Controllers;

import cz.vse.bagger.DAO.ProjectDao;
import cz.vse.bagger.Models.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.util.Objects;

public class ProjectController {

    private MainPageController mainPageController;

    @FXML TextField projectName;
    @FXML TextArea projectDescription;
    @FXML ListView<Project> projectsList;
    @FXML Button addMyTeam;

    public void initialize() throws SQLException, ClassNotFoundException {
        projectDescription.setEditable(false); //Project description is not implemented yet
        addMyTeam.setDisable(false);

        projectsList.getItems().clear();
        projectsList.getItems().addAll(ProjectDao.searchNotUsedProjects((RootLayoutController.loggedEmployeeTeam.getId_Team())));
    }
    /**
     *  Tato metoda slouží k tomu aby se mohla předat instance mainPageControlleru z jednoho mainPageControlleru do issueControlleru
     *
     *  @param mainPageController skrz tento parameter se předává instance mainPageControlleru
     */
    public void transferController(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }

    /**
     *  Tato metoda slouží pro vytvoření projektu tak, že si vezme z textFieldu jméno projektu a pak zavolá funkci
     */
    public void createProject(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String projectNameText = projectName.getText();
        Project project = ProjectDao.searchProject(projectNameText);
        if(projectNameText.equals("")){
            RootLayoutController.displayAlert("Missing fields", "One of the fields is missing value", "Please, check if you entered valid values into all fields");
        } else if (!(Objects.isNull(project))) {
            RootLayoutController.displayAlert("Error", "Existující projekt", "Zadaný projekt v databázi už existuje");
        }
        else {
            ProjectDao.insertProject(projectNameText);
            ProjectDao.insertTeam_projectRelationship(RootLayoutController.loggedEmployeeTeam.getId_Team(), ProjectDao.searchProject(projectNameText).getId_Project());

            projectsList.getItems().clear();
            projectsList.getItems().addAll(ProjectDao.searchNotUsedProjects((RootLayoutController.loggedEmployeeTeam.getId_Team())));

            mainPageController.reload();
            Stage stage = (Stage) addMyTeam.getScene().getWindow();
            stage.close();
        }
    }

    public void addMyTeamToProject(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Project selectedProject = projectsList.getSelectionModel().getSelectedItem();

        if(Objects.isNull(selectedProject))
            return;

        ProjectDao.insertTeamToProject(selectedProject.getId_Project(), RootLayoutController.loggedEmployeeTeam.getId_Team());

        projectsList.getItems().clear();
        projectsList.getItems().addAll(ProjectDao.searchNotUsedProjects((RootLayoutController.loggedEmployeeTeam.getId_Team())));

        mainPageController.reload();

    }
}
