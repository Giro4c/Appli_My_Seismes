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
        column1.setCellValueFactory(new PropertyValueFactory<>("attribute7"));
        column2.setCellValueFactory(new PropertyValueFactory<>("attribute8"));
        column3.setCellValueFactory(new PropertyValueFactory<>("attribute9"));
        column4.setCellValueFactory(new PropertyValueFactory<>("attribute10"));
        column5.setCellValueFactory(new PropertyValueFactory<>("attribute11"));
        column6.setCellValueFactory(new PropertyValueFactory<>("attribute12"));


        // mettre les données au tableau
        List<DonneesColonnes> data = new ArrayList<>();
        data.add(new DonneesColonnes("Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3"));
        data.add(new DonneesColonnes("Valeur 4", "Valeur 5", "Valeur 6", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3", "Valeur 1", "Valeur 2", "Valeur 3"));


        tableView.getItems().addAll(data);
    }


}