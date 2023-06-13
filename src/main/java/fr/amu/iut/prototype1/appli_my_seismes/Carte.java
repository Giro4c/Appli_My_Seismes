package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * La VBox représentative de l'interface graphique de la carte sismique.
 * @see MainControler#showMapSeismes()
 */
public class Carte extends VBox {

    public Carte() {

        super();
        this.setPrefSize(640, 600);

        /* Définit la plate-forme pour éviter "javafx.platform is not defined" */
        System.setProperty("javafx.platform", "desktop");

        /*
         * Définit l'user agent pour éviter l'exception
         * "Server returned HTTP response code: 403"
         */
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");

        /* Création de la carte Gluon JavaFX */
        MapView mapView = new MapView();

        /* Création du point avec latitude et longitude */
        MapPoint mapCenterPoint = new MapPoint(46.823600, 2.098766);

        /* Création et ajoute une couche à la carte */
        MapLayer mapLayer = new CustomSeismeMarkerLayer(MainControler.getListeSeismesTries());

        mapView.addLayer(mapLayer);


        /* Zoom de 5 */
        mapView.setZoom(6);

        /* Centre la carte sur le point */
        mapView.flyTo(0, mapCenterPoint, 0.1);

        this.getChildren().add(mapView);


    }

}
