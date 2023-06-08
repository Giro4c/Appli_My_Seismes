package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Filtrage extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(Filtrage.class.getResource("Filtrage.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Filtrage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}