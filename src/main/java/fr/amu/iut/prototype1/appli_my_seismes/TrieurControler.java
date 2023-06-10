package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Le controleur de la fenetre de paramétrage des filtres de tri d'une liste de séisme.
 */
public class TrieurControler extends VBox {

    private static ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    private ObservableList<Seisme> listeSeismesTrie = FXCollections.observableArrayList();

    // Tous les checkBox des attributs ----------------------------
    @FXML
    private CheckBox checkID;
    @FXML
    private CheckBox checkDate;
    @FXML
    private CheckBox checkHeure;
    @FXML
    private CheckBox checkNom;
    @FXML
    private CheckBox checkRegion;
    @FXML
    private CheckBox checkRGF;
    @FXML
    private CheckBox checkGPS;
    @FXML
    private CheckBox checkIntensite;
    @FXML
    private CheckBox checkChoc;
    @FXML
    private CheckBox checkQualiIntenEpi;

    // Tous les TextField qui contiennent les valeurs de filtrage ----------------------

    // MIN ------------------------------
    @FXML
    private TextField minID;
    @FXML
    private TextField minDateAnnee;
    @FXML
    private TextField minDateMois;
    @FXML
    private TextField minDateJour;
    @FXML
    private TextField minX;
    @FXML
    private TextField minY;
    @FXML
    private TextField minLatitude;
    @FXML
    private TextField minLongitude;
    @FXML
    private TextField minIntensite;
    // MAX ------------------------------
    @FXML
    private TextField maxID;
    @FXML
    private TextField maxDateAnnee;
    @FXML
    private TextField maxDateMois;
    @FXML
    private TextField maxDateJour;
    @FXML
    private TextField maxX;
    @FXML
    private TextField maxY;
    @FXML
    private TextField maxLatitude;
    @FXML
    private TextField maxLongitude;
    @FXML
    private TextField maxIntensite;
    // DEB ----------------------------------
    @FXML
    private TextField debNom;
    @FXML
    private TextField debRegion;
    @FXML
    private TextField debChoc;
    @FXML
    private TextField debQuali;
    // FIN ----------------------------------
    @FXML
    private TextField finNom;
    @FXML
    private TextField finRegion;
    @FXML
    private TextField finChoc;
    @FXML
    private TextField finQuali;
    // CON ----------------------------------
    @FXML
    private TextField conNom;
    @FXML
    private TextField conRegion;
    @FXML
    private TextField conChoc;
    @FXML
    private TextField conQuali;

    // Tous les spinners pour les filtres de l'attribut heure

    @FXML
    private Spinner<Integer> spinnerMinHeure;
    @FXML
    private Spinner<Integer> spinnerMinMinute;
    @FXML
    private Spinner<Integer> spinnerMinSeconde;
    @FXML
    private Spinner<Integer> spinnerMaxHeure;
    @FXML
    private Spinner<Integer> spinnerMaxMinute;
    @FXML
    private Spinner<Integer> spinnerMaxSeconde;

    // Le DatePicker pour le filtre date exacte
    @FXML
    private DatePicker exactDate;

    // Le bouton d'application des filtres validés
    @FXML
    private Button btnAppliqueFiltres;


    @FXML
    public void initialize(){
        // Réglage paramètres des spinners
        setUpSpinners();

        // Création des filtres
        IntegerFilter idFilter = new IntegerFilter(minID.textProperty(), maxID.textProperty());
        CalendarFilter calendarFilter = setUpCalendarFilter();
        StringFilter nomFilter = new StringFilter(debNom.textProperty(), finNom.textProperty(), conNom.textProperty());
        StringFilter regionFilter = new StringFilter(debRegion.textProperty(), finRegion.textProperty(), conRegion.textProperty());
        DoubleFilter xRGFFilter = new DoubleFilter(minX.textProperty(), maxX.textProperty());
        DoubleFilter yRGFFilter = new DoubleFilter(minY.textProperty(), maxY.textProperty());
        DoubleFilter latitudeFilter = new DoubleFilter(minLatitude.textProperty(), maxLatitude.textProperty());
        DoubleFilter longitudeFilter = new DoubleFilter(minLongitude.textProperty(), maxLongitude.textProperty());
        DoubleFilter intensiteFilter = new DoubleFilter(minIntensite.textProperty(), maxIntensite.textProperty());
        StringFilter chocFilter = new StringFilter(debChoc.textProperty(), finChoc.textProperty(), conChoc.textProperty());
        StringFilter qualiInteEpiFilter = new StringFilter(debQuali.textProperty(), finQuali.textProperty(), conQuali.textProperty());

        // Attribution de l'event de mise à jour de la liste de séismes triée par application de filtres
        btnAppliqueFiltres.setOnAction(actionEvent -> {
            ArrayList<Seisme> newSeismeList = new ArrayList<>();
            for (Seisme seisme : ControllerSeisme.getInitialListeSeismes()){
                if (checkID.isSelected() && !idFilter.matchFilter(seisme.getId())) continue;
                if (checkDate.isSelected() && !calendarFilter.matchDateFilter(seisme.getCalendar().getDateString())) continue;
                if (checkHeure.isSelected() && !calendarFilter.matchTimeFilter(seisme.getCalendar().getTimeString())) continue;
                if (checkNom.isSelected() && !nomFilter.matchFilter(seisme.getNom())) continue;
                if (checkRegion.isSelected() && !regionFilter.matchFilter(seisme.getRegion())) continue;
                if (checkRGF.isSelected() && !xRGFFilter.matchFilter(seisme.getxRGF93()) && !yRGFFilter.matchFilter(seisme.getyRGF93())) continue;
                if (checkGPS.isSelected() && !latitudeFilter.matchFilter(seisme.getLatitude()) && !longitudeFilter.matchFilter(seisme.getLongitude())) continue;
                if (checkIntensite.isSelected() && !intensiteFilter.matchFilter(seisme.getIntensite())) continue;
                if (checkChoc.isSelected() && !chocFilter.matchFilter(seisme.getChoc())) continue;
                if (checkQualiIntenEpi.isSelected() && !qualiInteEpiFilter.matchFilter(seisme.getQualiteIntensiteEpicentre())) continue;
                newSeismeList.add(seisme);
            }
            ControllerSeisme.getListeSeismesTries().setAll(newSeismeList);
        });

    }

    private void setUpSpinners(){
        ObservableList<Integer> compteurHeures = FXCollections.observableArrayList();
        for (int compteur = 0; compteur < 24; ++compteur){
            compteurHeures.add(compteur);
        }
        spinnerMinHeure.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(compteurHeures));
        spinnerMinHeure.getValueFactory().setValue(0);
        spinnerMaxHeure.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(compteurHeures));
        spinnerMaxHeure.getValueFactory().setValue(23);

        ObservableList<Integer> compteurMinEtSec = FXCollections.observableArrayList();
        for (int compteur = 0; compteur < 60; ++compteur){
            compteurMinEtSec.add(compteur);
        }
        spinnerMinMinute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(compteurMinEtSec));
        spinnerMinMinute.getValueFactory().setValue(0);
        spinnerMinSeconde.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(compteurMinEtSec));
        spinnerMinSeconde.getValueFactory().setValue(0);
        spinnerMaxMinute.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(compteurMinEtSec));
        spinnerMaxMinute.getValueFactory().setValue(59);
        spinnerMaxSeconde.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(compteurMinEtSec));
        spinnerMaxSeconde.getValueFactory().setValue(59);
    }

    private CalendarFilter setUpCalendarFilter(){
        // Property pour les time min et max car observable values non valables quand utilisées dans contructeur
        IntegerProperty propMinHeure = new SimpleIntegerProperty();
        propMinHeure.bind(spinnerMinHeure.valueProperty());
        IntegerProperty propMinMinute = new SimpleIntegerProperty();
        propMinMinute.bind(spinnerMinMinute.valueProperty());
        IntegerProperty propMinSeconde = new SimpleIntegerProperty();
        propMinSeconde.bind(spinnerMinSeconde.valueProperty());
        IntegerProperty propMaxHeure = new SimpleIntegerProperty();
        propMaxHeure.bind(spinnerMaxHeure.valueProperty());
        IntegerProperty propMaxMinute = new SimpleIntegerProperty();
        propMaxMinute.bind(spinnerMaxMinute.valueProperty());
        IntegerProperty propMaxSeconde = new SimpleIntegerProperty();
        propMaxSeconde.bind(spinnerMaxSeconde.valueProperty());

        // Retour de instanciation calendarFilter

        return new CalendarFilter(minDateAnnee.textProperty(), maxDateAnnee.textProperty(),
                minDateMois.textProperty(), maxDateMois.textProperty(), minDateJour.textProperty(), maxDateJour.textProperty(),
                propMinHeure, propMaxHeure, propMinMinute, propMaxMinute, propMinSeconde, propMaxSeconde);
    }

}
