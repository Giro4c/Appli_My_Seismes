package fr.amu.iut.prototype1.appli_my_seismes.functionalities.table_datas;

import fr.amu.iut.prototype1.appli_my_seismes.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * L'application représentative de la fenêtre du tableau de données.
 * @deprecated Remplacée par une fonction d'affichage de fenêtre sur MainControler
 * @see MainController#showTableDatas()
 */
public class TableDatas extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("functionalities/table_datas/TableDatas.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Données Sismique");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}

