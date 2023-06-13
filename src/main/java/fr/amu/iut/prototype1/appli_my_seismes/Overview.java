package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * L'application représentative de la fenetre Overview qui contient des données statistiques sur la liste de séismes traitée par l'application.
 * @deprecated Remplacée par une nouvelle fenetre dans MainControler
 * @see MainControler#showOverview()
 */
public class Overview extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox root = new VBox();


        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Overview");
        primaryStage.show();
    }

}
