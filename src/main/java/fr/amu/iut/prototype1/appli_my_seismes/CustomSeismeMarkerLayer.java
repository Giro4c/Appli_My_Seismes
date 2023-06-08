package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CustomSeismeMarkerLayer extends MapLayer {

    private ArrayList<MapPoint> mapPoints = new ArrayList<>();

    private ArrayList<Rectangle> markersRectangle = new ArrayList<>();


    /**
     * @param listeSeismes La liste de Séismes dont on va créer des marqueurs
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomSeismeMarkerLayer(ArrayList<Seisme> listeSeismes) {

        for (int indexSeisme = 0; indexSeisme < listeSeismes.size(); ++indexSeisme){
            // Declaration des points de map et marqueurs associé à un séisme
            mapPoints.add(new MapPoint(listeSeismes.get(indexSeisme).getLatitude(), listeSeismes.get(indexSeisme).getLongitude()));
            markersRectangle.add(new Rectangle(20, 20, Color.TRANSPARENT));
            markersRectangle.get(indexSeisme).setStroke(Utilitaire.richterColor(listeSeismes.get(indexSeisme).getIntensite().doubleValue()));
            markersRectangle.get(indexSeisme).setStrokeWidth(4);
            // Ajout des marqueurs à la couche
            this.getChildren().add(markersRectangle.get(indexSeisme));
        }

//        this.mapPoint = mapPoint;
//        this.mapPoint2 = new MapPoint(mapPoint.getLatitude() + 1.0, mapPoint.getLongitude() + 1.0);
//        this.mapPoint3 = new MapPoint(mapPoint.getLatitude() + 1.0, mapPoint.getLongitude());


        /* Rectangle rouge de taille 30x30 */
//        this.rectangle = new Rectangle(30, 30, Color.TRANSPARENT);
//        this.rectangle.setStyle("-fx-stroke: black; -fx-stroke-width: 5;");
//        /* Rectangle rouge de taille 30x30 */
//        this.rectangle2 = new Rectangle(30, 30);
//        this.rectangle2.setStyle("-fx-fill: green; -fx-stroke: black; -fx-stroke-width: 5;");
//
//        /* Cercle bleu de taille 5 */
//        this.circle2 = new Circle(5, Color.BLUE);
//
//        /* Ajoute les rectangles au MapLayer */
//        this.getChildren().add(rectangle);
//        this.getChildren().add(rectangle2);
//        /* Ajoute le cercle au MapLayer */
//        this.getChildren().add(circle2);

    }

    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {

        Point2D point2d;
        // Mise à jour de tous les marqueurs
        for (int index = 0; index < mapPoints.size(); ++index){
            /* Conversion du MapPoint vers Point2D */
            point2d = this.getMapPoint(mapPoints.get(index).getLatitude(), mapPoints.get(index).getLongitude());
            /* Déplace le marqueur selon les coordonnées du point */
            markersRectangle.get(index).setTranslateX(point2d.getX());
            markersRectangle.get(index).setTranslateY(point2d.getY());
        }

    }

}
