package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * L'application et fenêtre principale du projet qui donne aussi accès aux autres fenêtres du projet.
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Visualisateur de données sismiques");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
