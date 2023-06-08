package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.scene.shape.Circle;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class CustomSquareMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final MapPoint mapPoint2;
    private final Circle circle;
    private final Circle circle2;

    /**
     * @param mapPoint le point (latitude et longitude) où afficher le cercle
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomSquareMarkerLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
        this.mapPoint2 = new MapPoint(mapPoint.getLatitude() + 1.0, mapPoint.getLongitude() + 1.0);

        /* Cercle rouge de taille 5 */
        this.circle = new Circle(5, Color.RED);
        this.circle2 = new Circle(5, Color.BLUE);

        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle);
        this.getChildren().add(circle2);
    }

    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Déplace le cercle selon les coordonnées du point */
        circle.setTranslateX(point2d.getX());
        circle.setTranslateY(point2d.getY());

        /* Conversion du MapPoint vers Point2D */
        point2d = this.getMapPoint(mapPoint2.getLatitude(), mapPoint2.getLongitude());

        /* Déplace le cercle selon les coordonnées du point */
        circle2.setTranslateX(point2d.getX());
        circle2.setTranslateY(point2d.getY());
    }

}
