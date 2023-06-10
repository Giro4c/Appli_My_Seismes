package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerSeisme {
    @FXML
    private TableView<Seisme> tableView;

    @FXML
    private Button btnPrecedent;

    @FXML
    private Button btnSuivant;

    @FXML
    private Button btnDebut;

    @FXML
    private Button btnFin;

    @FXML
    private Button btnNumPPred;

    @FXML
    private Button btnNumPActu;

    @FXML
    private Button btnNumPSuiv;

    private ObservableList<Seisme> listSeismesPage = FXCollections.observableArrayList();
    // Lecture Du CSV choisi et conversion des lignes en objet Seisme
    private static ArrayList<Seisme> initialListeSeismes = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    private IntegerProperty numPageActuelle = new SimpleIntegerProperty(1);
    private final int COUNT_LINES = 15;
    private IntegerProperty totalPages;
    // Attributs pouvant être affectés par d'autres fenetres
    private static ArrayList<BooleanProperty> showColumn = new ArrayList<>();

    public static ArrayList<BooleanProperty> getShowColumn() {
        return showColumn;
    }


    @FXML
    public void initialize() {

        // Determination valeur du total de pages possible
        if (initialListeSeismes != null) {
            totalPages = new SimpleIntegerProperty(initialListeSeismes.size() / COUNT_LINES + 1);
        }
        else{
            totalPages = new SimpleIntegerProperty(1);
        }

        // Création des bindings
        createBindings();

        // Paramètrage de Table View
        setUpTable();

        // Déclaration de l'event handler
        EventHandler<ActionEvent> switchPage = actionEvent -> {
            Button btn = (Button) actionEvent.getSource();
            if (btn.getText().equals("Début")){
                numPageActuelle.setValue(1);
            }
            else if (btn.getText().equals("Précédent")){
                numPageActuelle.setValue(numPageActuelle.getValue() - 1);
            }
            else if (btn.getText().equals("Suivant")){
                numPageActuelle.setValue(numPageActuelle.getValue() + 1);
            }
            else if (btn.getText().equals("Fin")){
                numPageActuelle.setValue(totalPages.getValue());
            }
            else {
                // On considère que le label est forcément un nombre car toutes les valeurs
                // de texte que peuvent avoir un bouton spécifique au changement de page ont déjà
                // été vérifiées. C'est donc un numéro de page.

                numPageActuelle.setValue(Integer.valueOf(btn.getText()));
            }

            // Mise à jour des données du tableau
            reloadData();

        };

        // Evenements boutons de changement de page
        btnSuivant.setOnAction(switchPage);
        btnPrecedent.setOnAction(switchPage);
        btnDebut.setOnAction(switchPage);
        btnFin.setOnAction(switchPage);
        btnNumPActu.setOnAction(switchPage);
        btnNumPPred.setOnAction(switchPage);
        btnNumPSuiv.setOnAction(switchPage);

    }

    private void createBindings(){
        // Binding pour les boutons vers Précédent
        btnNumPPred.textProperty().bind(Bindings.concat("", Bindings.subtract(numPageActuelle, 1)));
        btnNumPPred.visibleProperty().bind(Bindings.when(Bindings.lessThan(numPageActuelle, 2)).then(false).otherwise(true));
        btnPrecedent.visibleProperty().bind(Bindings.when(Bindings.lessThan(numPageActuelle, 2)).then(false).otherwise(true));
        // Binding pour les boutons vers Suivant
        btnNumPSuiv.textProperty().bind(Bindings.concat("", Bindings.add(numPageActuelle, 1)));
        btnNumPSuiv.visibleProperty().bind(Bindings.when(Bindings.equal(totalPages, numPageActuelle)).then(false).otherwise(true));
        btnSuivant.visibleProperty().bind(Bindings.when(Bindings.equal(totalPages, numPageActuelle)).then(false).otherwise(true));
        // Binding pour les boutons vers Actuel
        btnNumPActu.textProperty().bind(Bindings.concat("", numPageActuelle));

    }

    private void setUpTable(){
        // Association des attributs de Seisme à des colonnes du tableau
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("heure"));
        tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("region"));
        tableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("choc"));
        tableView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("xRGF93"));
        tableView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("yRGF93"));
        tableView.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("latitude"));
        tableView.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("longitude"));
        tableView.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("intensite"));
        tableView.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("qualiteIntensiteEpicentre"));

        // Remplissage du TableView avec la liste des Seismes
        reloadData();

        // Initialisation liste des visibilités de colonnes
        setUpShowColumn();

        // Liaison de la visibilité des colonnes à la liste de booléens showColumn
        for (int indexColumn = 0; indexColumn < showColumn.size(); ++indexColumn){
            tableView.getColumns().get(indexColumn).visibleProperty().bind(showColumn.get(indexColumn));
        }

    }

    private void reloadData(){
        // On vide la liste des séismes de la page
        listSeismesPage.clear();
        int startIndex = (numPageActuelle.getValue() - 1) * COUNT_LINES;
        // De l'index du séisme de début de page au l'index inférieur au plus petit entre : l'index du premier seisme de la page suivante ou la taille de listeSeismes
        for (int indexSeisme = startIndex; indexSeisme < Math.min(startIndex + COUNT_LINES, initialListeSeismes.size()); ++indexSeisme){
            listSeismesPage.add(initialListeSeismes.get(indexSeisme));
        }
        // Mise à jour dans tableView
        tableView.getItems().setAll(listSeismesPage);
    }

    private void setUpShowColumn(){
        // ID
        showColumn.add(new SimpleBooleanProperty(true));
        // Date
        showColumn.add(new SimpleBooleanProperty(true));
        // Heure
        showColumn.add(new SimpleBooleanProperty(true));
        // Nom
        showColumn.add(new SimpleBooleanProperty(false));
        // Region
        showColumn.add(new SimpleBooleanProperty(true));
        // Choc
        showColumn.add(new SimpleBooleanProperty(false));
        // x RGF93
        showColumn.add(new SimpleBooleanProperty(false));
        // y RGF93
        showColumn.add(new SimpleBooleanProperty(false));
        // Latitude
        showColumn.add(new SimpleBooleanProperty(false));
        // Longitude
        showColumn.add(new SimpleBooleanProperty(false));
        // Intensité
        showColumn.add(new SimpleBooleanProperty(true));
        // Qualité intensité épicentre
        showColumn.add(new SimpleBooleanProperty(true));
    }

    @FXML
    public void openParamsAffichage() throws IOException {
        FXMLLoader fxmlloaderFiltreAffich = new FXMLLoader(Filtrage.class.getResource("Filtrage.fxml"));
        Stage stageFiltreAffiche = new Stage();
        stageFiltreAffiche.setTitle("Paramètres d'affichage");
        stageFiltreAffiche.setScene(new Scene(fxmlloaderFiltreAffich.load()));
        stageFiltreAffiche.show();
    }


}