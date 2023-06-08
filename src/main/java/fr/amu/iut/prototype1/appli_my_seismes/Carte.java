package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Carte extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        /* Définit la plate-forme pour éviter "javafx.platform is not defined" */
        System.setProperty("javafx.platform", "desktop");

        /*
         * Définit l'user agent pour éviter l'exception
         * "Server returned HTTP response code: 403"
         */
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        VBox root = new VBox();

        /* Création de la carte Gluon JavaFX */
        MapView mapView = new MapView();

        /* Création du point avec latitude et longitude */
        MapPoint mapCenterPoint = new MapPoint(46.823600, 2.098766);

        /* Création et ajoute une couche à la carte */
        ArrayList<Seisme> listSeisme = CSVReader.StringArrayToSeismeArrayList(CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
        MapLayer mapLayer = new CustomSeismeMarkerLayer(listSeisme);
        mapView.addLayer(mapLayer);

        /* Zoom de 5 */
        mapView.setZoom(6);

        /* Centre la carte sur le point */
        mapView.flyTo(0, mapCenterPoint, 0.1);

        root.getChildren().add(mapView);

        /*
         * IMPORTANT mettre la taille de la fenêtre pour éviter l'erreur
         * java.lang.OutOfMemoryError
         */
        Scene scene = new Scene(root, 640, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Carte sismique");
        primaryStage.show();

    }

}
