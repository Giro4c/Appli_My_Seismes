package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ControllerOverview {

//    private ControllerSeisme controllerSeisme;

    private static ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));





//    public void setControllerSeisme (ControllerSeisme controllerSeisme){
//        this.controllerSeisme = controllerSeisme;
//    }

}
