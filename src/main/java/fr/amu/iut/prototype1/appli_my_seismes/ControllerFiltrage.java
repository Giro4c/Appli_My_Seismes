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
    private CheckBox checkChoc;
    @FXML
    private CheckBox checkX;
    @FXML
    private CheckBox checkY;
    @FXML
    private CheckBox checkLatitude;
    @FXML
    private CheckBox checkLongitude;
    @FXML
    private CheckBox checkIntensite;
    @FXML
    private CheckBox checkQuali;
    @FXML
    private Button btnAppliqueFiltre;


    @FXML
    public void initialize() {

    }


    @FXML
    public void EnvoiFiltre () {
        boolean[] newShowColumn = FiltreTab();
        for (int indexColumn = 0; indexColumn < newShowColumn.length; ++indexColumn){
            ControllerSeisme.getShowColumn().get(indexColumn).setValue(newShowColumn[indexColumn]);
        }
    }

    public boolean[] FiltreTab() {
        // Récupérer l'état de chaque case à cocher
        // et Stocker les booléens dans un tableau
        boolean[] listFiltreBool = {
                checkID.isSelected(),            // 0
                checkDate.isSelected(),          // 1
                checkHeure.isSelected(),         // 2
                checkNom.isSelected(),           // 3
                checkRegion.isSelected(),        // 4
                checkChoc.isSelected(),          // 5
                checkX.isSelected(),             // 6
                checkY.isSelected(),             // 7
                checkLatitude.isSelected(),      // 8
                checkLongitude.isSelected(),     // 9
                checkIntensite.isSelected(),     // 10
                checkQuali.isSelected()        // 11
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

