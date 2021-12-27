package cz.vse.bagger.Models;

import javafx.beans.property.*;

import java.util.Date;

public class Issue {
    private IntegerProperty Id_Issue;
    private IntegerProperty Id_Project;
    private IntegerProperty Id_Creater;
    private IntegerProperty Id_Closer;
    private StringProperty Issue_Title;
    private StringProperty Issue_Description;
    private SimpleObjectProperty<Date> Start_Date;
    private SimpleObjectProperty<Date> End_Date;
    private IntegerProperty Importance;

    @Override
    public String toString() {
        return Issue_Title.getValue();
    }

    public Issue() {
        this.Id_Issue = new SimpleIntegerProperty();
        this.Id_Project = new SimpleIntegerProperty();
        this.Id_Creater = new SimpleIntegerProperty();
        this.Id_Closer = new SimpleIntegerProperty();
        this.Issue_Title = new SimpleStringProperty();
        this.Issue_Description = new SimpleStringProperty();
        this.Start_Date = new SimpleObjectProperty<>();
        this.End_Date = new SimpleObjectProperty<>();
        this.Importance = new SimpleIntegerProperty();
    }

    public int getId_Issue() {
        return Id_Issue.get();
    }

    public IntegerProperty id_IssueProperty() {
        return Id_Issue;
    }

    public void setId_Issue(int id_Issue) {
        this.Id_Issue.set(id_Issue);
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

    public int getId_Creater() {
        return Id_Creater.get();
    }

    public IntegerProperty id_CreaterProperty() {
        return Id_Creater;
    }

    public void setId_Creater(int id_Creater) {
        this.Id_Creater.set(id_Creater);
    }

    public int getId_Closer() {
        return Id_Closer.get();
    }

    public IntegerProperty id_CloserProperty() {
        return Id_Closer;
    }

    public void setId_Closer(int id_Closer) {
        this.Id_Closer.set(id_Closer);
    }

    public String getIssue_Title() {
        return Issue_Title.get();
    }

    public StringProperty issue_TitleProperty() {
        return Issue_Title;
    }

    public void setIssue_Title(String issue_Title) {
        this.Issue_Title.set(issue_Title);
    }

    public String getIssue_Description() {
        return Issue_Description.get();
    }

    public StringProperty issue_DescriptionProperty() {
        return Issue_Description;
    }

    public void setIssue_Description(String issue_Description) {
        this.Issue_Description.set(issue_Description);
    }

    public Date getStart_Date() {
        return Start_Date.get();
    }

    public SimpleObjectProperty<Date> start_DateProperty() {
        return Start_Date;
    }

    public void setStart_Date(Date start_Date) {
        this.Start_Date.set(start_Date);
    }

    public Date getEnd_Date() {
        return End_Date.get();
    }

    public SimpleObjectProperty<Date> end_DateProperty() {
        return End_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.End_Date.set(end_Date);
    }

    public int getImportance() {
        return Importance.get();
    }

    public IntegerProperty importanceProperty() {
        return Importance;
    }

    public void setImportance(int importance) {
        this.Importance.set(importance);
    }
}
