package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        //Implemantation de la listeSeismes de test
        ListeSeismes listeSeismes = new ListeSeismes();
        ArrayList<Seisme> listeSeisme = listeSeismes.listeTestSeismes();


        // remplissage du tableview par la listeTest avec boucle for
        List<DonneesColonnes> data = new ArrayList<>();

        for (Seisme seisme : listeSeisme) {
            // Convertissage en string des attributs
            String attribut1 = Integer.toString(seisme.getId());
            String attribut2 = seisme.getDateString();
            String attribut3 = Integer.toString(seisme.getHeure());
            String attribut4 = seisme.getNom();
            String attribut5 = seisme.getRegion();
            String attribut6 = Integer.toString(seisme.getChoc());
            String attribut7 = Double.toString(seisme.getxRGF93());
            String attribut8 = Double.toString(seisme.getyRGF93());
            String attribut9 = Double.toString(seisme.getLatitude());
            String attribut10 = Double.toString(seisme.getLongitude());
            String attribut11 = Integer.toString(seisme.getIntensite());
            String attribut12 = seisme.getQualiteIntensiteEpicentre();



            // Créez une instance de DonneesColonnes avec les attributs convertis
            DonneesColonnes donnees = new DonneesColonnes(attribut1, attribut2, attribut3, attribut4, attribut5, attribut6, attribut7, attribut8,attribut9, attribut10, attribut11, attribut12);

            // Ajout des données a la liste data
            data.add(donnees);
        }








//        data.add(new DonneesColonnes("Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3"));
//        data.add(new DonneesColonnes("Valeur 4", "Valeur 5", "Valeur 6", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3"));
//        data.add(new DonneesColonnes("Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3"));



        tableView.getItems().addAll(data);
    }


}