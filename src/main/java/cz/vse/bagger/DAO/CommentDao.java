package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDao {
    public static ObservableList<Comment> searchComments (int Id_Issue) throws SQLException, ClassNotFoundException {
        String selectStmt = "SELECT * FROM Comment where Id_Issue="+Id_Issue;

        try {
            ResultSet resultComments = DBUtil.dbExecuteQuery(selectStmt);
            ObservableList<Comment> commentsList = getCommentsList(resultComments);
            return commentsList;
        } catch (SQLException exception) {
            System.out.println("SQL select operation has been failed: " + exception);
            throw exception;
        }
    }

    private static ObservableList<Comment> getCommentsList(ResultSet resultComments) throws SQLException, ClassNotFoundException {
        ObservableList<Comment> commentList = FXCollections.observableArrayList();

        while (resultComments.next()) {
            Comment comment = new Comment();;
            comment.setId_Comment(resultComments.getInt("Id_Comment"));
            comment.setId_Employee(resultComments.getInt("Id_Employee"));
            comment.setId_Issue(resultComments.getInt("Id_Issue"));
            comment.setMessage(resultComments.getString("Message"));
            commentList.add(comment);
        }
        return commentList;
    }

    public static void insertEmp (int Id_Employee, int Id_Issue, String Message) throws SQLException, ClassNotFoundException {
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO Comments\n" +
                        "(Id_Employee, Id_Issue, Message)\n" +
                        "VALUES\n" +
                        "('"+Id_Employee+"', '"+Id_Issue+"','"+Message+"');\n" +
                        "END;";

        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException exception) {
            System.out.print("Error occurred while DELETE Operation: " + exception);
            throw exception;
        }
    }
}
