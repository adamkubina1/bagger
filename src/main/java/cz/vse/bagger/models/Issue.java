package cz.vse.bagger.models;

import javafx.beans.property.*;

import java.util.Date;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Issue {
    private IntegerProperty idIssue;
    private IntegerProperty idProject;
    private IntegerProperty idCreater;
    private StringProperty issueTitle;
    private StringProperty issueDescription;
    private SimpleObjectProperty<Date> startDate;
    private IntegerProperty importance;

    @Override
    public String toString() {
        return issueTitle.getValue();
    }

    public Issue() {
        this.idIssue = new SimpleIntegerProperty();
        this.idProject = new SimpleIntegerProperty();
        this.idCreater = new SimpleIntegerProperty();
        this.issueTitle = new SimpleStringProperty();
        this.issueDescription = new SimpleStringProperty();
        this.startDate = new SimpleObjectProperty<>();
        this.importance = new SimpleIntegerProperty();
    }

    public int getIdIssue() {
        return idIssue.get();
    }

    public IntegerProperty idIssueProperty() {
        return idIssue;
    }

    public void setIdIssue(int idIssue) {
        this.idIssue.set(idIssue);
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

    public int getIdCreater() {
        return idCreater.get();
    }

    public IntegerProperty idCreaterProperty() {
        return idCreater;
    }

    public void setIdCreater(int idCreater) {
        this.idCreater.set(idCreater);
    }

    public String getIssueTitle() {
        return issueTitle.get();
    }

    public StringProperty issueTitleProperty() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle.set(issueTitle);
    }

    public String getIssueDescription() {
        return issueDescription.get();
    }

    public StringProperty issueDescriptionProperty() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription.set(issueDescription);
    }

    public Date getStartDate() {
        return startDate.get();
    }

    public SimpleObjectProperty<Date> startDateProperty() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate.set(startDate);
    }

    public int getImportance() {
        return importance.get();
    }

    public IntegerProperty importanceProperty() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance.set(importance);
    }
}
