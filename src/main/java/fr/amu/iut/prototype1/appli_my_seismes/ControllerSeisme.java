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

    public void initialize() {
        column1.setCellValueFactory(new PropertyValueFactory<>("attribute1"));
        column2.setCellValueFactory(new PropertyValueFactory<>("attribute2"));
        column3.setCellValueFactory(new PropertyValueFactory<>("attribute3"));

        // mettre les données au tableau
        List<DonneesColonnes> data = new ArrayList<>();
        data.add(new DonneesColonnes("Valeur 1", "Valeur 2", "Valeur 3"));
        data.add(new DonneesColonnes("Valeur 4", "Valeur 5", "Valeur 6"));

        tableView.getItems().addAll(data);
    }


}