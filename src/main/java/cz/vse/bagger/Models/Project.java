package cz.vse.bagger.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {
    private IntegerProperty Id_Project;
    private StringProperty Project_Name;

    public Project() {
        this.Id_Project = new SimpleIntegerProperty();
        this.Project_Name = new SimpleStringProperty();
    }

    public int getId_Project() {
        return Id_Project.get();
    }

    public IntegerProperty id_ProjectProperty() {
        return Id_Project;
    }

    public void setId_Project(int id_Project) {
        this.Id_Project.set(id_Project);
    }

    public String getProject_Name() {
        return Project_Name.get();
    }

    public StringProperty project_NameProperty() {
        return Project_Name;
    }

    public void setProject_Name(String project_Name) {
        this.Project_Name.set(project_Name);
    }
}
