package fr.amu.iut.prototype1.appli_my_seismes.functionalities.table_datas;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

import javafx.scene.layout.VBox;

/**
 * La classe controleur de la fenetre des filtres d'affichage.
 */
public class DisplayFilteringController extends VBox {


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
//    @FXML
//    private Button btnAppliqueFiltre;


    @FXML
    public void initialize() {

    }

    /**
     * Envoie les données du filtre de la fenetre pour modifier la liste des visibilités des colonnes du tableau de données
     * de ControllerSeisme.
     * @see TableDatasController#getShowColumn()
     */
    @FXML
    public void EnvoiFiltre () {
        boolean[] newShowColumn = FiltreTab();
        for (int indexColumn = 0; indexColumn < newShowColumn.length; ++indexColumn){
            TableDatasController.getShowColumn().get(indexColumn).setValue(newShowColumn[indexColumn]);
        }
    }

    /**
     * Crée un array de booléens représentant l'état de chaque CheckBox. Si une CheckBox est sélectionnée, on considère que
     * l'attribut qui lui est associé est à afficher sur un tableau de données.
     * @return Un array de booléens représentant l'état de sélection de chaque CheckBox.
     * @see #EnvoiFiltre()
     */
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

}

