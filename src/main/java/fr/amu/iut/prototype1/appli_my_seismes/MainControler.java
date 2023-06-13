package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Le controleur de la fenetre d'accueil princiaple de l'application du projet.
 */
public class MainControler extends VBox {

    @FXML
    Button btnTable;
    @FXML
    Button btnMap;
    @FXML
    Button btnOverview;
    @FXML
    TextField fieldCsvPath;

    private static final String DEFAULT_CSV_PATH = "src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv";

    private final static ArrayList<Seisme> initialListeSeismes = initInitialListeSeismes();
    private static ObservableList<Seisme> listeSeismesTries = FXCollections.observableArrayList(initialListeSeismes);

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
//        listeSeismesTries = FXCollections.observableArrayList(initialListeSeismes);


    }

    /**
     * Affiche la fenêtre du tableau de données par lequel on peut aussi accéder aux fenêtres de filtre d'affichage et de filtre
     * de tri sur la liste initiale de séismes.
     * @throws IOException
     */
    @FXML
    public void showTableDatas() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AfficheurDonnees.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stageTable = new Stage();
        stageTable.setTitle("Données Sismique");
        stageTable.setScene(scene);
        stageTable.show();
    }

    /**
     * Affiche la fenêtre de la carte de France des séismes.
     */
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

    /**
     * Affiche la fenêtre de l'overview sur les données statistiques de la liste de séisme actuelle.
     */
    @FXML
    public void showOverview() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stageTable = new Stage();
        stageTable.setTitle("Overview");
        stageTable.setScene(scene);
        stageTable.show();

    }

    @FXML
    public void loadNewCSV(){

    }

    private static ArrayList<Seisme> initInitialListeSeismes(){
        try {
            return CSVReader.StringArrayToSeismeArrayList(
                    CSVReader.CSVFileReader(DEFAULT_CSV_PATH));
        } catch (FileNotFoundException e) {
            return new ArrayList<Seisme>();
        }
    }

}
