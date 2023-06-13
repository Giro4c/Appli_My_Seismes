package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * La VBox représentative de l'interface graphique de la carte sismique.
 * @see MainControler#showMapSeismes()
 */
public class Carte extends VBox {

    public Carte() {

        super();
        this.setPrefSize(640, 680);
        this.setStyle("-fx-background-color: #263553;");
        setUpMapView();
        setUpLegend();

    }

    private void setUpMapView(){
//        VBox mapContainer = new VBox();
        this.setPrefSize(640, 680);

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
//        this.getChildren().add(mapContainer);

    }

    private void setUpLegend(){
        HBox bottomBox = new HBox(newHomeBtn(), newLegend());
        this.getChildren().add(bottomBox);
    }

    private Button newHomeBtn(){
        Button btnHome = new Button();
        btnHome.setText("");
        btnHome.setPrefSize(80, 80);
        btnHome.setStyle("-fx-background-color: transparent;");
        ImageView imgHomeBtn = new ImageView(new Image(getClass().getResourceAsStream("imgHome.png")));
        imgHomeBtn.setFitHeight(80);
        imgHomeBtn.setFitWidth(80);
        btnHome.setGraphic(imgHomeBtn);
        return btnHome;
    }

    /**
     * Crée la partie légende de la carte sismique sous forme de VBox.
     * @return Une VBox représentant la légende de la carte
     */
    private VBox newLegend(){

        Stop[] stops = new Stop[] { new Stop(0, Utilitaire.richterColor(0)), new Stop(1, Utilitaire.richterColor(10))};
        LinearGradient richterGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
        Rectangle rectangleLegend = new Rectangle(0, 0, 300, 35);
        rectangleLegend.setFill(richterGradient);

        Label titleMap = new Label("Carte des séismes en France et leur intensité :");
        titleMap.setTextFill(Color.WHITESMOKE);
        Label labelMinInt = new Label("0");
        labelMinInt.setTextFill(Color.WHITESMOKE);
        Label labelMaxInt = new Label("10 et plus");
        labelMaxInt.setTextFill(Color.WHITESMOKE);

        HBox gradientBox = new HBox(labelMinInt, rectangleLegend, labelMaxInt);
        gradientBox.setAlignment(Pos.CENTER);
        gradientBox.setSpacing(20);
        VBox mapInfosBox = new VBox(titleMap, gradientBox);
        mapInfosBox.setSpacing(7);
        mapInfosBox.setPadding(new Insets(15, 10, 10, 30));

        return mapInfosBox;

    }

}
