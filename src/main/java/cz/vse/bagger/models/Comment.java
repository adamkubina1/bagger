package cz.vse.bagger.models;
import javafx.beans.property.*;

/**
 *  Modelova trida pro databazovou entitu
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Comment {
    private IntegerProperty idComment;
    private IntegerProperty idEmployee;
    private IntegerProperty idIssue;
    private StringProperty message;

    public Comment() {
        this.idComment = new SimpleIntegerProperty();
        this.idEmployee = new SimpleIntegerProperty();
        this.idIssue = new SimpleIntegerProperty();
        this.message = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return message.getValue();
    }

    public int getIdComment() {
        return idComment.get();
    }

    public IntegerProperty idCommentProperty() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment.set(idComment);
    }

    public int getIdEmployee() {
        return idEmployee.get();
    }

    public IntegerProperty idEmployeeProperty() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee.set(idEmployee);
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

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
    }
}
