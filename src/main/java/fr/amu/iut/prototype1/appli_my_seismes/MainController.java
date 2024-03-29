package fr.amu.iut.prototype1.appli_my_seismes;

import fr.amu.iut.prototype1.appli_my_seismes.Main;
import fr.amu.iut.prototype1.appli_my_seismes.datas.Seisme;
import fr.amu.iut.prototype1.appli_my_seismes.functionalities.seismic_map.SeismicMap;
import fr.amu.iut.prototype1.appli_my_seismes.utilitaries.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Le controleur de la fenetre d'accueil princiaple de l'application du projet.
 */
public class MainController extends VBox {

    @FXML
    Button btnTable;
    @FXML
    Button btnMap;
    @FXML
    Button btnOverview;
    @FXML
    TextField fieldCsvPath;
    @FXML
    Label labelCsvError;

    private static final String DEFAULT_CSV_PATH = "src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/utilitaries/SisFrance_seismes_20230604151458.csv";

    private static ArrayList<Seisme> initialListeSeismes = initInitialListeSeismes();
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("functionalities/table_datas/TableDatas.fxml"));

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
    public void showMapSeismes() throws IOException {

        /*
         * IMPORTANT mettre la taille de la fenêtre pour éviter l'erreur
         * java.lang.OutOfMemoryError
         */
        Scene scene = new Scene(new SeismicMap(), 640, 680);

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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("functionalities/overview/Overview.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stageOverview = new Stage();
        stageOverview.setTitle("Overview");
        stageOverview.setScene(scene);
        stageOverview.setResizable(false);
        stageOverview.show();

    }

    /**
     * Affiche la fenêtre de la page d'accueil de l'application.<br>
     * Fonction disponible à l'utilisation pour toutes les classes.
     */
    public static void showHomePage() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stageHome = new Stage();
        stageHome.setTitle("Visualisateur de données sismiques");
        stageHome.setScene(scene);
        stageHome.show();

    }

    @FXML
    public void loadNewCSV(){

//        System.out.println("Action text field path csv");
        if (fieldCsvPath.getText().equals("")){
            initialListeSeismes = initInitialListeSeismes();
            listeSeismesTries.setAll(initialListeSeismes);
            labelCsvError.setVisible(false);
//            System.out.println("Fichier lu et listes mises à jour");
        }
        else {
            try {
                initialListeSeismes = CSVReader.StringArrayToSeismeArrayList(CSVReader.CSVFileReader(fieldCsvPath.getText()));
                listeSeismesTries.setAll(initialListeSeismes);
                labelCsvError.setVisible(false);
//                System.out.println("Fichier lu et listes mises à jour");
            } catch (Exception e){
                labelCsvError.setVisible(true);
//                System.out.println("Fichier non lu, erreur de chemin");
            }
        }

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
