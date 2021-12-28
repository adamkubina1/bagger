package cz.vse.bagger.DAO;

import cz.vse.bagger.Models.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Comment
 */
public class CommentDao {
    /**
     *  Tato metoda vyhledá z databáze všechny komentáře které mají konkrétní Issue id.
     *  @param Id_Issue Id issue říká ke kterému issue chceme vypsat komentáře.
     */
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
    /**
     *  Tato metoda zpracovává výsledek ze searchComments metody a záznamy které ta metoda vrátila dává do Listu.
     *  @param resultComments je výsledek který vrátila databáze na naše querry.
     */
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
    /**
     *  Tato metoda ukládá nový komentář do databáze.
     *  @param Id_Employee je Id zaměstnance který vkládá komentář.
     *  @param Id_Issue je Id problému ke kterému přidáváme komentář
     *  @param Message zpráva kterou ukládáme k danému issue
     */
    public static void insertComment (int Id_Employee, int Id_Issue, String Message) throws SQLException, ClassNotFoundException {
        String updateStmt =
                        "INSERT INTO Comment\n" +
                        "(Id_Employee, Id_Issue, Message)\n" +
                        "VALUES\n" +
                        "('"+Id_Employee+"', '"+Id_Issue+"','"+Message+"');";
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
}
