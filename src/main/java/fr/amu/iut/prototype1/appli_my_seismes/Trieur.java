package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Trieur extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficheurDonnees.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Paramètres de filtrage");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
