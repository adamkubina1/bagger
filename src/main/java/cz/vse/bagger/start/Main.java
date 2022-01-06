package cz.vse.bagger.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 *  Root class
 *
 *  @author Adam Kubina, Jiri Omacht, Martin Kalina
 */

public class Main extends Application {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));

        stage.setScene(new Scene(root));
        stage.show();
    }
}



