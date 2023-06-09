package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import fr.amu.iut.prototype1.appli_my_seismes.ControllerSeisme;

import java.util.ArrayList;


public class ControllerFiltrage {


    private ControllerSeisme controllerSeisme;

    @FXML
    private CheckBox idbox;

    @FXML
    private CheckBox datebox;

    @FXML
    private CheckBox heurebox;

    @FXML
    private CheckBox nombox;

    @FXML
    private CheckBox regionbox;

    @FXML
    private CheckBox chocbox;

    @FXML
    private CheckBox Xbox;

    @FXML
    private CheckBox Ybox;

    @FXML
    private CheckBox Latitudebox;

    @FXML
    private CheckBox Longitudebox;

    @FXML
    private CheckBox Intensitebox;

    @FXML
    private CheckBox qualitebox;

    @FXML
    private Button appliquer;

    public void setControllerSeisme(ControllerSeisme controllerSeisme) {
        this.controllerSeisme = controllerSeisme;
    }

    @FXML
    public void initialize() {








    }



//    public boolean[] FiltreTab() {
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
//        boolean VerifBoxQualite = qualite.isSelected();   }







      public void initCheckBox(boolean isVisibleID, boolean isVisibleDate, boolean isVisibleHeure, boolean isVisibleNom, boolean isVisibleRegion, boolean isVisibleChoc,
                boolean isVisibleX, boolean isVisibleY, boolean isVisibleLatitude, boolean isVisibleLongitude, boolean isVisibleIntensite, boolean isVisibleQualite) {
        idbox.setSelected(isVisibleID);
        datebox.setSelected(isVisibleDate);
        heurebox.setSelected(isVisibleHeure);
        nombox.setSelected(isVisibleNom);
        regionbox.setSelected(isVisibleRegion);
        chocbox.setSelected(isVisibleChoc);
        Xbox.setSelected(isVisibleX);
        Ybox.setSelected(isVisibleY);
        Latitudebox.setSelected(isVisibleLatitude);
        Longitudebox.setSelected(isVisibleLongitude);
        Intensitebox.setSelected(isVisibleIntensite);
        qualitebox.setSelected(isVisibleQualite);
    }


    public void AppliqueFiltrage() {
        // Récupérer l'état de chaque case à cocher
        idbox.setSelected(idbox.isSelected());
        datebox.setSelected(datebox.isSelected());
        heurebox.setSelected(heurebox.isSelected());
        nombox.setSelected(nombox.isSelected());
        regionbox.setSelected(regionbox.isSelected());
        chocbox.setSelected(chocbox.isSelected());
        Xbox.setSelected(Xbox.isSelected());
        Ybox.setSelected(Ybox.isSelected());
        Latitudebox.setSelected(Latitudebox.isSelected());
        Longitudebox.setSelected(Longitudebox.isSelected());
        Intensitebox.setSelected(Intensitebox.isSelected());
        qualitebox.setSelected(qualitebox.isSelected());





//        boolean VerifBoxID = idbox.isSelected();
//        boolean VerifBoxDate = datebox.isSelected();
//        boolean VerifBoxHeure = heurebox.isSelected();
//        boolean VerifBoxNom = nombox.isSelected();
//        boolean VerifBoxRegion = regionbox.isSelected();
//        boolean VerifBoxChoc = chocbox.isSelected();
//        boolean VerifBoxX = Xbox.isSelected();
//        boolean VerifBoxY = Ybox.isSelected();
//        boolean VerifBoxLatitude = Latitudebox.isSelected();
//        boolean VerifBoxLongitude = Longitudebox.isSelected();
//        boolean VerifBoxIntensite = Intensitebox.isSelected();
//        boolean VerifBoxQualite = qualitebox.isSelected();

//        controllerSeisme.filtrageAttribut(VerifBoxID, VerifBoxDate, VerifBoxHeure, VerifBoxNom, VerifBoxRegion, VerifBoxChoc,
//                VerifBoxX, VerifBoxY, VerifBoxLatitude, VerifBoxLongitude, VerifBoxIntensite, VerifBoxQualite);

    }


    public boolean[] getEtatBox() {
        boolean[] etats = new boolean[12]; // Tableau de booléens pour stocker l'état des 12 CheckBox

        etats[0] = idbox.isSelected();
        etats[1] = datebox.isSelected();
        etats[2] = heurebox.isSelected();
        etats[3] = nombox.isSelected();
        etats[4] = regionbox.isSelected();
        etats[5] = chocbox.isSelected();
        etats[6] = Xbox.isSelected();
        etats[7] = Ybox.isSelected();
        etats[8] = Latitudebox.isSelected();
        etats[9] = Longitudebox.isSelected();
        etats[10] = Intensitebox.isSelected();
        etats[11] = qualitebox.isSelected();

        return etats;
    }



//        ControllerSeisme.filtrageAttribut(VerifBoxID, VerifBoxDate, VerifBoxHeure, VerifBoxNom, VerifBoxRegion, VerifBoxChoc,
//                VerifBoxX, VerifBoxY, VerifBoxLatitude, VerifBoxLongitude, VerifBoxIntensite, VerifBoxQualite);

    }

