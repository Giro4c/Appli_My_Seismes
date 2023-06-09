package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class ControllerSeisme {

    @FXML
    private TableView<DonneesColonnes> tableView;

    @FXML
    private TableColumn<DonneesColonnes, String> column1;

    @FXML
    private TableColumn<DonneesColonnes, String> column2;

    @FXML
    private TableColumn<DonneesColonnes, String> column3;

    @FXML
    private TableColumn<DonneesColonnes, String> column4;

    @FXML
    private TableColumn<DonneesColonnes, String> column5;

    @FXML
    private TableColumn<DonneesColonnes, String> column6;

    @FXML
    private TableColumn<DonneesColonnes, String> column7;

    @FXML
    private TableColumn<DonneesColonnes, String> column8;

    @FXML
    private TableColumn<DonneesColonnes, String> column9;

    @FXML
    private TableColumn<DonneesColonnes, String> column10;

    @FXML
    private TableColumn<DonneesColonnes, String> column11;

    @FXML
    private TableColumn<DonneesColonnes, String> column12;

    @FXML
    private Button boutonfiltre;

    private ControllerFiltrage controllerFiltrage;


    public void setControllerFiltrage(ControllerFiltrage controller) {
        this.controllerFiltrage = controller;
    }



    public void initialize() {
        column1.setCellValueFactory(new PropertyValueFactory<>("attribute1"));
        column2.setCellValueFactory(new PropertyValueFactory<>("attribute2"));
        column3.setCellValueFactory(new PropertyValueFactory<>("attribute3"));
        column4.setCellValueFactory(new PropertyValueFactory<>("attribute4"));
        column5.setCellValueFactory(new PropertyValueFactory<>("attribute5"));
        column6.setCellValueFactory(new PropertyValueFactory<>("attribute6"));
        column7.setCellValueFactory(new PropertyValueFactory<>("attribute7"));
        column8.setCellValueFactory(new PropertyValueFactory<>("attribute8"));
        column9.setCellValueFactory(new PropertyValueFactory<>("attribute9"));
        column10.setCellValueFactory(new PropertyValueFactory<>("attribute10"));
        column11.setCellValueFactory(new PropertyValueFactory<>("attribute11"));
        column12.setCellValueFactory(new PropertyValueFactory<>("attribute12"));


        // Lecture Du CSV choisi et conversion des lignes en objet Seisme
        ArrayList<String> CSVString = CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv");
        ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(CSVString);


        // remplissage du tableview par la listeTest avec boucle for
        List<DonneesColonnes> data = new ArrayList<>();

        for (Seisme seisme : listeSeisme) {
            // Convertissage en string des attributs
            String attribut1 = seisme.getId() != null ? Integer.toString(seisme.getId()) : "";
            String attribut2 = seisme.getCalendar().getDateString();
            String attribut3 = seisme.getCalendar().getTimeString();
            String attribut4 = seisme.getNom();
            String attribut5 = seisme.getRegion();
            String attribut6 = seisme.getChoc();
            String attribut7 = seisme.getxRGF93() != null ? Double.toString(seisme.getxRGF93()) : "";
            String attribut8 = seisme.getyRGF93() != null ? Double.toString(seisme.getyRGF93()) : "";
            String attribut9 = seisme.getLatitude() != null ? Double.toString(seisme.getLatitude()) : "";
            String attribut10 = seisme.getLongitude() != null ? Double.toString(seisme.getLongitude()) : "";
            String attribut11 = seisme.getIntensite() != null ? Double.toString(seisme.getIntensite()) : "";
            String attribut12 = seisme.getQualiteIntensiteEpicentre();




            // Créez une instance de DonneesColonnes avec les attributs convertis
            DonneesColonnes donnees = new DonneesColonnes(attribut1, attribut2, attribut3, attribut4, attribut5, attribut6, attribut7, attribut8, attribut9, attribut10, attribut11, attribut12);

            // Ajout des données a la liste data
            data.add(donnees);
        }


        tableView.getItems().addAll(data);




//        tableView.getColumns().get(5).setVisible(false);

//        TableColumn<DonneesColonnes, String> colonneASupprimer = (TableColumn<DonneesColonnes, String>) tableView.getColumns().get(1);
//        tableView.getColumns().remove(colonneASupprimer);

    }


//    public void OuvreFiltrage() throws IOException {
//        Filtrage filtrage = new Filtrage();
//        filtrage.start(new Stage());
//
//    }

    @FXML
    private void OuvreFiltrage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Filtrage.fxml"));
        Parent root = loader.load();

        ControllerFiltrage controllerFiltrage = loader.getController();
        controllerFiltrage.setControllerSeisme(this);
        controllerFiltrage.initCheckBox(tableView.getColumns().get(0).isVisible(), tableView.getColumns().get(1).isVisible(), tableView.getColumns().get(2).isVisible(),
                tableView.getColumns().get(3).isVisible(), tableView.getColumns().get(4).isVisible(), tableView.getColumns().get(5).isVisible(), tableView.getColumns().get(6).isVisible(),
                tableView.getColumns().get(7).isVisible(), tableView.getColumns().get(8).isVisible(), tableView.getColumns().get(9).isVisible(), tableView.getColumns().get(10).isVisible(),
                tableView.getColumns().get(11).isVisible());


        Stage stage = new Stage();
        stage.setTitle("Filtrage");
        stage.setScene(new Scene(root));

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node) event.getSource()).getScene().getWindow());
        stage.show();
    }

    public void AppliquerFiltrage(boolean idbox, boolean datebox, boolean heurebox, boolean nombox, boolean regionbox, boolean chocbox,
                                        boolean xbox, boolean ybox, boolean latitudebox, boolean longitudebox, boolean intensitebox, boolean qualitebox) {

        tableView.getColumns().get(0).setVisible(idbox);
        tableView.getColumns().get(1).setVisible(datebox);
        tableView.getColumns().get(2).setVisible(heurebox);
        tableView.getColumns().get(3).setVisible(nombox);
        tableView.getColumns().get(4).setVisible(regionbox);
        tableView.getColumns().get(5).setVisible(chocbox);
        tableView.getColumns().get(6).setVisible(xbox);
        tableView.getColumns().get(7).setVisible(ybox);
        tableView.getColumns().get(8).setVisible(latitudebox);
        tableView.getColumns().get(9).setVisible(longitudebox);
        tableView.getColumns().get(10).setVisible(intensitebox);
        tableView.getColumns().get(11).setVisible(qualitebox);



//        VerifBoxID = idbox;
//        VerifBoxDate = datebox;
//        VerifBoxHeure = heurebox;
//        VerifBoxNom = nombox;
//        VerifBoxRegion = regionbox;
//        VerifBoxChoc = chocbox;
//        VerifBoxX = xbox;
//        VerifBoxY = ybox;
//        VerifBoxLatitude = latitudebox;
//        VerifBoxLongitude = longitudebox;
//        VerifBoxIntensite = intensitebox;
//        VerifBoxQualite = qualitebox;


    }
    public void updateCheckBox(boolean[] etats) {
        tableView.getColumns().get(0).setVisible(etats[0]);
        tableView.getColumns().get(1).setVisible(etats[1]);
        tableView.getColumns().get(2).setVisible(etats[2]);
        tableView.getColumns().get(3).setVisible(etats[3]);
        tableView.getColumns().get(4).setVisible(etats[4]);
        tableView.getColumns().get(5).setVisible(etats[5]);
        tableView.getColumns().get(6).setVisible(etats[6]);
        tableView.getColumns().get(7).setVisible(etats[7]);
        tableView.getColumns().get(8).setVisible(etats[8]);
        tableView.getColumns().get(9).setVisible(etats[9]);
        tableView.getColumns().get(10).setVisible(etats[10]);
        tableView.getColumns().get(11).setVisible(etats[11]);

    }






}