package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AfficheurDonnees extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficheurDonnees.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Données Sismique");
        primaryStage.setScene(scene);
        primaryStage.show();
        //ListeSeismes.listeTestSeismes();

    }

}

