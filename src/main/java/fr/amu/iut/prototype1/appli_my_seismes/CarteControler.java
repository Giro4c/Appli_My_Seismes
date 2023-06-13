package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CarteControler extends VBox {

    @FXML
    VBox mapVBox;
    @FXML
    HBox bottomBox;
    @FXML
    Button btnHome;

    @FXML
    public void initialize(){
        Carte carte = new Carte();
        mapVBox.getChildren().setAll(carte.getChildren());
    }


}
