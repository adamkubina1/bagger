package cz.vse.bagger.Models;
import javafx.beans.property.*;
import java.sql.Date;

public class Comment {
    private IntegerProperty Id_Comment;
    private IntegerProperty Id_Employee;
    private IntegerProperty Id_Issue;
    private StringProperty Message;

    public Comment() {
        this.Id_Comment = new SimpleIntegerProperty();
        this.Id_Employee = new SimpleIntegerProperty();
        this.Id_Issue = new SimpleIntegerProperty();
        this.Message = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return Message.getValue();
    }

    public int getId_Comment() {
        return Id_Comment.get();
    }

    public IntegerProperty id_CommentProperty() {
        return Id_Comment;
    }

    public void setId_Comment(int id_Comment) {
        this.Id_Comment.set(id_Comment);
    }

    public int getId_Employee() {
        return Id_Employee.get();
    }

    public IntegerProperty id_EmployeeProperty() {
        return Id_Employee;
    }

    public void setId_Employee(int id_Employee) {
        this.Id_Employee.set(id_Employee);
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

    public String getMessage() {
        return Message.get();
    }

    public StringProperty messageProperty() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message.set(message);
    }
}
