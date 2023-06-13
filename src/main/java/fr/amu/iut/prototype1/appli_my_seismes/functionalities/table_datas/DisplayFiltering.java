package fr.amu.iut.prototype1.appli_my_seismes.functionalities.table_datas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * L'application représentative de la fenetre des filtres d'affichage d'un tableau de données.
 * @deprecated Remplacée par une fenetre accessible via
 */
public class DisplayFiltering extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlloader = new FXMLLoader(DisplayFiltering.class.getResource("functionalities/table_datas/DisplayFiltering.fxml"));
        Scene scene = new Scene(fxmlloader.load());
        stage.setTitle("Filtrage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}