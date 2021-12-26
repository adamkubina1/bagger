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

}
