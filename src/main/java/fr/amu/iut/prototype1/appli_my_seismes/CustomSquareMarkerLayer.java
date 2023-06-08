package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.scene.shape.Circle;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomSquareMarkerLayer extends MapLayer {

    private final MapPoint mapPoint;
    private final MapPoint mapPoint2;
    private final MapPoint mapPoint3;
    private final Rectangle rectangle;
    private final Rectangle rectangle2;
    private final Circle circle2;


    /**
     * @param mapPoint le point (latitude et longitude) où afficher le cercle
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomSquareMarkerLayer(MapPoint mapPoint) {
        this.mapPoint = mapPoint;
        this.mapPoint2 = new MapPoint(mapPoint.getLatitude() + 1.0, mapPoint.getLongitude() + 1.0);
        this.mapPoint3 = new MapPoint(mapPoint.getLatitude() + 1.0, mapPoint.getLongitude());


        /* Rectangle rouge de taille 30x30 */
        this.rectangle = new Rectangle(30, 30, Color.TRANSPARENT);
        this.rectangle.setStyle("-fx-stroke: black; -fx-stroke-width: 5;");
        /* Rectangle rouge de taille 30x30 */
        this.rectangle2 = new Rectangle(30, 30);
        this.rectangle2.setStyle("-fx-fill: green; -fx-stroke: black; -fx-stroke-width: 5;");

        /* Cercle bleu de taille 5 */
        this.circle2 = new Circle(5, Color.BLUE);

        /* Ajoute les rectangles au MapLayer */
        this.getChildren().add(rectangle);
        this.getChildren().add(rectangle2);
        /* Ajoute le cercle au MapLayer */
        this.getChildren().add(circle2);
    }

    /* La fonction est appelée à chaque rafraichissement de la carte */
    @Override
    protected void layoutLayer() {
        /* Conversion du MapPoint vers Point2D */
        Point2D point2d = this.getMapPoint(mapPoint.getLatitude(), mapPoint.getLongitude());

        /* Déplace le rectangle selon les coordonnées du point */
        rectangle.setTranslateX(point2d.getX());
        rectangle.setTranslateY(point2d.getY());

        point2d = this.getMapPoint(mapPoint3.getLatitude(), mapPoint3.getLongitude());

        /* Déplace le rectangle selon les coordonnées du point */
        rectangle2.setTranslateX(point2d.getX());
        rectangle2.setTranslateY(point2d.getY());

        point2d = this.getMapPoint(mapPoint2.getLatitude(), mapPoint2.getLongitude());

        /* Déplace le cercle selon les coordonnées du point */
        circle2.setTranslateX(point2d.getX());
        circle2.setTranslateY(point2d.getY());
    }

}
