package fr.amu.iut.prototype1.appli_my_seismes;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CustomSeismeMarkerLayer extends MapLayer {

    private ArrayList<MapPoint> mapPoints = new ArrayList<>();

    private ArrayList<Rectangle> markersRectangle = new ArrayList<>();
    private ObservableList<Seisme> listSeismes;


    /**
     * @param listeSeismes La liste de Séismes dont on va créer des marqueurs
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomSeismeMarkerLayer(ObservableList<Seisme> listeSeismes) {

        listSeismes = listeSeismes;

        reloadData();

        ListChangeListener<Seisme> updatelistener = change -> {
            change.next();
            reloadData();
        };
        listSeismes.addListener(updatelistener);

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

    public void reloadData(){
        // Réinitialisation des arrays conteneurs
        this.getChildren().clear();
        mapPoints.clear();
        markersRectangle.clear();

        double markerSize;
        double strokeWidth;
        if (listSeismes.size() > 3000){
            markerSize = 5;
            strokeWidth = 1;
        }
        else if (listSeismes.size() > 1500){
            markerSize = 10;
            strokeWidth = 2.5;
        }
        else{
            markerSize = 20;
            strokeWidth = 4;
        }

        for (Seisme seisme : listSeismes){

            if (seisme.getLatitude() != null && seisme.getLongitude() != null) {
                // Declaration des points de map et marqueurs associés à un séisme
                mapPoints.add(new MapPoint(seisme.getLatitude(), seisme.getLongitude()));
                markersRectangle.add(new Rectangle(markerSize, markerSize, Color.TRANSPARENT));
                markersRectangle.get(markersRectangle.size()-1).setStrokeWidth(strokeWidth);
                if (seisme.getIntensite() != null) {
                    markersRectangle.get(markersRectangle.size()-1).setStroke(Utilitaire.richterColor(seisme.getIntensite().doubleValue()));
                }
                else {
                    markersRectangle.get(markersRectangle.size()-1).setStroke(Color.BLACK);
                }

                // Ajout des marqueurs à la couche
                this.getChildren().add(markersRectangle.get(markersRectangle.size()-1));
            }
        }
    }

}
