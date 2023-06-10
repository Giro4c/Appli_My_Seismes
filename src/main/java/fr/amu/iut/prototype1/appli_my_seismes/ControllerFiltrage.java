package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import fr.amu.iut.prototype1.appli_my_seismes.ControllerSeisme;

import java.util.ArrayList;


public class ControllerFiltrage {


    @FXML
    private CheckBox id;

    @FXML
    private CheckBox date;

    @FXML
    private CheckBox heure;

    @FXML
    private CheckBox nom;

    @FXML
    private CheckBox region;

    @FXML
    private CheckBox choc;

    @FXML
    private CheckBox X;

    @FXML
    private CheckBox Y;

    @FXML
    private CheckBox Latitude;

    @FXML
    private CheckBox Longitude;

    @FXML
    private CheckBox Intensité;

    @FXML
    private CheckBox qualite;

    @FXML
    private Button appliquer;


    @FXML
    public void initialize() {

    }


    public void EnvoiFiltre () {

    }

    public boolean[] FiltreTab() {
        // Récupérer l'état de chaque case à cocher
        boolean VerifBoxID = id.isSelected();
        boolean VerifBoxDate = date.isSelected();
        boolean VerifBoxHeure = heure.isSelected();
        boolean VerifBoxNom = nom.isSelected();
        boolean VerifBoxRegion = region.isSelected();
        boolean VerifBoxChoc = choc.isSelected();
        boolean VerifBoxX = X.isSelected();
        boolean VerifBoxY = Y.isSelected();
        boolean VerifBoxLatitude = Latitude.isSelected();
        boolean VerifBoxLongitude = Longitude.isSelected();
        boolean VerifBoxIntensite = Intensité.isSelected();
        boolean VerifBoxQualite = qualite.isSelected();

        // Stocker les booléens dans un tableau
        boolean[] listFiltreBool = {
                VerifBoxID, VerifBoxDate, VerifBoxHeure, VerifBoxNom, VerifBoxRegion, VerifBoxChoc,
                VerifBoxX, VerifBoxY, VerifBoxLatitude, VerifBoxLongitude, VerifBoxIntensite, VerifBoxQualite
        };

        return listFiltreBool;
    }

//    public void AppliqueFiltrage() {
//        // Récupérer l'état de chaque case à cocher
//        boolean VerifBoxID = id.isSelected();
//        boolean VerifBoxDate = date.isSelected();
//        boolean VerifBoxHeure = heure.isSelected();
//        boolean VerifBoxNom = nom.isSelected();
//        boolean VerifBoxRegion = region.isSelected();
//        boolean VerifBoxChoc = choc.isSelected();
//        boolean VerifBoxX = X.isSelected();
//        boolean VerifBoxY = Y.isSelected();
//        boolean VerifBoxLatitude = Latitude.isSelected();
//        boolean VerifBoxLongitude = Longitude.isSelected();
//        boolean VerifBoxIntensite = Intensité.isSelected();
//        boolean VerifBoxQualite = qualite.isSelected();
//
//        ControllerSeisme.filtrageAttribut(VerifBoxID, VerifBoxDate, VerifBoxHeure, VerifBoxNom, VerifBoxRegion, VerifBoxChoc,
//                VerifBoxX, VerifBoxY, VerifBoxLatitude, VerifBoxLongitude, VerifBoxIntensite, VerifBoxQualite);
//
//    }


//        ControllerSeisme.filtrageAttribut(VerifBoxID, VerifBoxDate, VerifBoxHeure, VerifBoxNom, VerifBoxRegion, VerifBoxChoc,
//                VerifBoxX, VerifBoxY, VerifBoxLatitude, VerifBoxLongitude, VerifBoxIntensite, VerifBoxQualite);

    }

