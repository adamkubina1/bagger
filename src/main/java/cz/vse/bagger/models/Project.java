package cz.vse.bagger.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Project {
    private IntegerProperty idProject;
    private StringProperty projectName;

    public Project() {
        this.idProject = new SimpleIntegerProperty();
        this.projectName = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return projectName.getValue();
    }

    public int getIdProject() {
        return idProject.get();
    }

    public IntegerProperty idProjectProperty() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject.set(idProject);
    }

    public String getProjectName() {
        return projectName.get();
    }

    public StringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }
}
