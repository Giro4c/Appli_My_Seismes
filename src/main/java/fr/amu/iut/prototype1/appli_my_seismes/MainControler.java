package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainControler extends VBox {

    @FXML
    Button btnTable;
    @FXML
    Button btnMap;
    @FXML
    Button btnOverview;

    private final static ArrayList<Seisme> initialListeSeismes = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    private static ObservableList<Seisme> listeSeismesTries;

    // Lecture Du CSV choisi et conversion des lignes en objet Seisme
    public static ArrayList<Seisme> getInitialListeSeismes() {
        return initialListeSeismes;
    }
    public static ObservableList<Seisme> getListeSeismesTries() {
        return listeSeismesTries;
    }

    @FXML
    public void initialize(){

        // Initialisation liste variable de séismes
        listeSeismesTries = FXCollections.observableArrayList(initialListeSeismes);

    }

    @FXML
    public void showTableDatas() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficheurDonnees.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stageTable = new Stage();
        stageTable.setTitle("Données Sismique");
        stageTable.setScene(scene);
        stageTable.show();
    }

    @FXML
    public void showMapSeismes(){

        /*
         * IMPORTANT mettre la taille de la fenêtre pour éviter l'erreur
         * java.lang.OutOfMemoryError
         */
        Scene scene = new Scene(new Carte(), 640, 600);
        Stage stageMap = new Stage();
        stageMap.setScene(scene);
        stageMap.setTitle("Carte sismique");
        stageMap.show();

    }

    @FXML
    public void showOverview(){

    }

}
