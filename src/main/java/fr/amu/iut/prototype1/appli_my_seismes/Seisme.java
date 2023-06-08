package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.*;

import java.util.ArrayList;

/**
 * Une classe resprésentant un séisme qui en contient les caractéristiques suivantes :
 * <ul>
 *     <li>Identifiant</li>
 *     <li>Date (Année, Mois et Jour)</li>
 *     <li>Heure (Heure, Minutes et Secondes)</li>
 *     <li>Nom</li>
 *     <li>Région</li>
 *     <li>Choc</li>
 *     <li>X en RFG93</li>
 *     <li>Y en RFG93</li>
 *     <li>Latitude</li>
 *     <li>Longitude</li>
 *     <li>Intensité</li>
 *     <li>Qualité de l'intensité à l'épicentre</li>
 * </ul>
 * @author Camille Girodengo
 */
public class Seisme {

    public static final int COUNT_ATTRIBUTE_LABELS = 12;
    public static final int DEFAULT_INVALID_ID = -1;
    public static final double DEFAULT_INVALID_XRGF93 = 1300001;
    public static final double DEFAULT_INVALID_YRGF93 = 7200001;
    public static final double DEFAULT_INVALID_LATITUDE = 361;
    public static final double DEFAULT_INVALID_LONGITUDE = 361;
    public static final double DEFAULT_INVALID_INTENSITE = -1;
    private static ArrayList<String> initialListLabelsAttributs = new ArrayList<>();



    private final IntegerProperty id;
    private final CalendarWithNulls calendar;
    private final StringProperty nom;
    private final StringProperty region;
    private final StringProperty choc;
    private final DoubleProperty xRGF93;
    private final DoubleProperty yRGF93;
    private final DoubleProperty latitude;
    private final DoubleProperty longitude;
    private final DoubleProperty intensite;
    private final StringProperty qualiteIntensiteEpicentre;
    private final StringProperty date;
    private StringProperty heure;

    public Seisme(Integer id, String date, String heure, String nom, String region,
                  String choc, Double xRGF93, Double yRGF93, Double latitude, Double longitude,
                  Double intensite, String qualiteIntensiteEpicentre) {
        super();
        this.calendar = new CalendarWithNulls(date, heure);
        // Ces valeurs sont toutes observables mais constantes
        this.nom = new SimpleStringProperty(nom);
        this.region = new SimpleStringProperty(region);
        this.choc = new SimpleStringProperty(choc);
        this.qualiteIntensiteEpicentre = new SimpleStringProperty(qualiteIntensiteEpicentre);
        this.date = new SimpleStringProperty(calendar.getDateString());
        this.heure = new SimpleStringProperty(calendar.getTimeString());

        // Pour éviter NullPointerExeption :
        if (id == null){
            this.id = new SimpleIntegerProperty(DEFAULT_INVALID_ID);
        }
        else {
            this.id = new SimpleIntegerProperty(id);
        }
        if (xRGF93 == null){
            this.xRGF93 = new SimpleDoubleProperty(DEFAULT_INVALID_XRGF93);
        }
        else {
            this.xRGF93 = new SimpleDoubleProperty(xRGF93);
        }
        if (yRGF93 == null){
            this.yRGF93 = new SimpleDoubleProperty(DEFAULT_INVALID_YRGF93);
        }
        else {
            this.yRGF93 = new SimpleDoubleProperty(yRGF93);
        }
        if (latitude == null){
            this.latitude = new SimpleDoubleProperty(DEFAULT_INVALID_LATITUDE);
        }
        else {
            this.latitude = new SimpleDoubleProperty(latitude);
        }
        if (longitude == null){
            this.longitude = new SimpleDoubleProperty(DEFAULT_INVALID_LONGITUDE);
        }
        else {
            this.longitude = new SimpleDoubleProperty(longitude);
        }
        if (intensite == null){
            this.intensite = new SimpleDoubleProperty(DEFAULT_INVALID_INTENSITE);
        }
        else {
            this.intensite = new SimpleDoubleProperty(intensite);
        }

    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seisme seisme = (Seisme) o;
        return (this.id == seisme.id && this.calendar.equals(seisme.calendar) && this.nom.equals(seisme.nom) &&
                this.region.equals(seisme.region) && this.choc == seisme.choc && this.xRGF93 == seisme.xRGF93 &&
                this.yRGF93 == seisme.yRGF93 && this.latitude == seisme.latitude && this.longitude == seisme.longitude &&
                this.intensite == seisme.intensite && this.qualiteIntensiteEpicentre.equals(qualiteIntensiteEpicentre));
    }

    @Override
    public String toString(){
        return "Seisme [id=" + id +
                ", date=" + calendar.getDateString() +
                ", heure=" + calendar.getTimeString() +
                ", nom=" + nom +
                ", region=" + region +
                ", choc=" + choc +
                ", xRGF93=" + xRGF93 +
                ", yRGF93=" + yRGF93 +
                ", latitude=" + latitude +
                ", logitude=" + longitude +
                ", intensite=" + intensite +
                ", qualiteIntensiteEpicentre=" + qualiteIntensiteEpicentre + "]";

    }

    public static ArrayList<String> getInitialListLabelsAttributs() {
        return initialListLabelsAttributs;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty heureProperty() {
        return heure;
    }

    public String getQualiteIntensiteEpicentre() {
        return qualiteIntensiteEpicentre.getValue();
    }

    public StringProperty qualiteIntensiteEpicentreProperty() {
        return qualiteIntensiteEpicentre;
    }

    public Double getIntensite() {
        return intensite.getValue();
    }

    public DoubleProperty intensiteProperty() {
        return intensite;
    }

    public Double getLongitude() {
        return longitude.getValue();
    }

    public DoubleProperty longitudeProperty() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude.getValue();
    }

    public DoubleProperty latitudeProperty() {
        return latitude;
    }

    public Double getyRGF93() {
        return yRGF93.getValue();
    }

    public DoubleProperty yRGF93Property() {
        return yRGF93;
    }

    public Double getxRGF93() {
        return xRGF93.getValue();
    }

    public DoubleProperty xRGF93Property() {
        return xRGF93;
    }

    public String getChoc() {
        return choc.getValue();
    }

    public StringProperty chocProperty() {
        return choc;
    }

    public String getRegion() {
        return region.getValue();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public String getNom() {
        return nom.getValue();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public CalendarWithNulls getCalendar() {
        return calendar;
    }

    public Integer getId() {
        return id.getValue();
    }

}

