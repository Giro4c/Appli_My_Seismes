package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class ControlleurSeisme2 {
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
    private static ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    private IntegerProperty numPageActuelle = new SimpleIntegerProperty(1);
    private final int COUNT_LINES = 15;
    private IntegerProperty totalPages;

    public void initialize() {

        // Determination valeur du total de pages possible
        if (listeSeisme != null) {
            totalPages = new SimpleIntegerProperty(listeSeisme.size() / COUNT_LINES + 1);
        }
        else{
            totalPages = new SimpleIntegerProperty(1);
        }

        // Création des bindings
        createBindings();

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

    private void reloadData(){
        // On vide la liste des séismes de la page
        listSeismesPage.clear();
        int startIndex = (numPageActuelle.getValue() - 1) * COUNT_LINES;
        // De l'index du séisme de début de page au l'index inférieur au plus petit entre : l'index du premier seisme de la page suivante ou la taille de listeSeismes
        for (int indexSeisme = startIndex; indexSeisme < Math.min(startIndex + COUNT_LINES, listeSeisme.size()); ++indexSeisme){
            listSeismesPage.add(listeSeisme.get(indexSeisme));
        }
        // Mise à jour dans tableView
        tableView.getItems().setAll(listSeismesPage);

    }


}