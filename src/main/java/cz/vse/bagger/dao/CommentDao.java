package cz.vse.bagger.dao;

import cz.vse.bagger.models.Comment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *  Tato třída slouží pro zpracování příkazů k tabulkce Comment
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */
public class CommentDao {
    /**
     *  Tato metoda vyhledá z databáze všechny komentáře které mají konkrétní Issue id.
     *  @param idIssue Id issue říká ke kterému issue chceme vypsat komentáře.
     */
    public static ObservableList<Comment> searchComments (int idIssue) throws SQLException, ClassNotFoundException {
        final String SELECT_STMT = "SELECT * FROM Comment where Id_Issue="+idIssue;

        try {
            ResultSet resultComments = DBUtil.dbExecuteQuery(SELECT_STMT);
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
    private static ObservableList<Comment> getCommentsList(ResultSet resultComments) throws SQLException {
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
     *  @param idEmployee je Id zaměstnance který vkládá komentář.
     *  @param idIssue je Id problému ke kterému přidáváme komentář
     *  @param message zpráva kterou ukládáme k danému issue
     */
    public static void insertComment (int idEmployee, int idIssue, String message) throws SQLException, ClassNotFoundException {
        final String UPDATE_STMT =
                        "INSERT INTO Comment\n" +
                        "(Id_Employee, Id_Issue, message)\n" +
                        "VALUES\n" +
                        "('"+idEmployee+"', '"+idIssue+"','"+message+"');";
        try {
            DBUtil.dbExecuteUpdate(UPDATE_STMT);
        } catch (SQLException exception) {
            System.out.print("Error occurred while INSERT Operation: " + exception);
            throw exception;
        }
    }
}
