package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

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
    private Button btnPrecedent;

    @FXML
    private Button btnSuivant;

    @FXML
    private Button btnDebut;

    @FXML
    private Button btnFin;

    @FXML
    private Label PagePrecedent;

    @FXML
    private Label Pageactuel;

    @FXML
    private Label TotalPages;

    @FXML
    private Label PageSuivante;
    private List<DonneesColonnes> data;
    private List<Seisme> listeSeisme;
    private int Pageactu;
    private int Nbligne = 15;


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
        listeSeisme = CSVReader.StringArrayToSeismeArrayList(CSVString);


        // Remplissage du TableView avec la liste des Seismes
        data = new ArrayList<>();
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
        // Affiche la première page
        int totalPages = (int) Math.ceil((double) listeSeisme.size() / Nbligne);
        showPage(1, totalPages);

        // evenement bouton suivant,precedent,debut,fin
        btnSuivant.setOnAction(event -> {
            if (Pageactu < totalPages) {
                showPage(Pageactu + 1, totalPages);
            }
        });

        btnPrecedent.setOnAction(event -> {
            if (Pageactu > 1) {
                showPage(Pageactu - 1, totalPages);
            }
        });

        btnDebut.setOnAction(event -> {
            showPage(1, totalPages);
        });

        btnFin.setOnAction(event -> {
            showPage(totalPages, totalPages);
        });
    }

    private void showPage(int page, int totalPages) {
        // Met à jour la page actuelle
        Pageactu = page;
        PagePrecedent.setText(String.valueOf(page - 1));
        Pageactuel.setText(String.valueOf(page));
        TotalPages.setText(String.valueOf(totalPages));
        PageSuivante.setText(String.valueOf(page + 1));

        int debut = (page - 1) * Nbligne;
        int fin = Math.min(debut + Nbligne, listeSeisme.size());

        // Efface le contenu de la liste pageData
        List<DonneesColonnes> pageData = new ArrayList<>();

        // Ajoute les nouvelles données à la liste pageData
        for (int i = debut; i < fin; i++) {
            Seisme seisme = listeSeisme.get(i);
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

            DonneesColonnes donnees = new DonneesColonnes(attribut1, attribut2, attribut3, attribut4, attribut5, attribut6, attribut7, attribut8, attribut9, attribut10, attribut11, attribut12);
            pageData.add(donnees);
        }

        // Met à jour le TableView avec les données de la page
        tableView.getItems().setAll(pageData);

        // Gére l'état du bouton "Suivant"
        btnSuivant.setDisable(page >= totalPages);
    }


}