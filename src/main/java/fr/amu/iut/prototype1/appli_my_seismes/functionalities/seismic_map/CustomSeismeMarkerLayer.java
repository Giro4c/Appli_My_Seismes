package fr.amu.iut.prototype1.appli_my_seismes.functionalities.seismic_map;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import fr.amu.iut.prototype1.appli_my_seismes.datas.Seisme;
import fr.amu.iut.prototype1.appli_my_seismes.utilitaries.Utilitaire;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Un claque custom de carte spécifique aux séismes. Chaque calque de cette classe est lié à une ObservableList de Séismes
 * dont une mise à jour entraine un rafraichissement des listes de mapPoint et de marqueurs.
 * Les marqueurs sont des carrés transparents avec bordure. La couleur de la bordure d'un marqueur dépend de l'intensité
 * du seisme qui lui est associé sur l'echelle de Richter.
 */
public class CustomSeismeMarkerLayer extends MapLayer {

    private ArrayList<MapPoint> mapPoints = new ArrayList<>();

    private ArrayList<Rectangle> markersRectangle = new ArrayList<>();
    private ObservableList<Seisme> listSeismes;


    /**
     * @param listeSeismes La liste de Séismes dont on va créer des marqueurs
     * @see com.gluonhq.maps.MapPoint
     */
    public CustomSeismeMarkerLayer(ObservableList<Seisme> listeSeismes) {

        // Initialisation de la couche
        listSeismes = listeSeismes;
        reloadData();

        // Ajout d'un change listener pour mise à jour automatique de la couche
        ListChangeListener<Seisme> updatelistener = change -> {
            change.next();
            // Rechager la liste de séismes et les marqueurs
            reloadData();
            // Rafraichier la map pour mettre à jour la position des marqueurs. Au sinon s'affichent dans le coin jusqu'à rafraichissement.
            layoutLayer();
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

    /**
     * Charge ou recharge les marqueurs de la couche ainsi que tous les MapPoint.
     * Cette méthode s'appelle soit lors de l'initialisation, soit lorsque la liste de séismes à afficher est mise à jour.
     * @see Utilitaire#richterColor(double)
     */
    public void reloadData(){
        // Réinitialisation des arrays conteneurs
        this.getChildren().clear();
        mapPoints.clear();
        markersRectangle.clear();

        /* Mise en place de variables de taille et d'épaisseur dépendantes du nombre de séismes à afficher
            pour améliorer la visibilité sur la carte */

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

        // Pour chaque séisme de la liste...
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
