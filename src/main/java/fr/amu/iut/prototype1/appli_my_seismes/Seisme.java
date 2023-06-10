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



    private final Integer id;
    private final CalendarWithNulls calendar;
    private final StringProperty nom;
    private final StringProperty region;
    private final StringProperty choc;
    private final Double xRGF93;
    private final Double yRGF93;
    private final Double latitude;
    private final Double longitude;
    private final Double intensite;
    private final StringProperty qualiteIntensiteEpicentre;
    private final StringProperty date;
    private final StringProperty heure;

    // Attributs StringProperty pour tous les integers et doubles
    private final StringProperty stringID;
    private final StringProperty stringXRGF93;
    private final StringProperty stringYRGF93;
    private final StringProperty stringLatitude;
    private final StringProperty stringLongitude;
    private final StringProperty stringIntensite;

    public Seisme(Integer id, String date, String heure, String nom, String region,
                  String choc, Double xRGF93, Double yRGF93, Double latitude, Double longitude,
                  Double intensite, String qualiteIntensiteEpicentre) {
        super();
        this.calendar = new CalendarWithNulls(date, heure);
        this.id = id;
        this.xRGF93 = xRGF93;
        this.yRGF93 = yRGF93;
        this.latitude = latitude;
        this.longitude = longitude;
        this.intensite = intensite;
        // Ces valeurs sont toutes observables mais constantes
        this.nom = new SimpleStringProperty(nom);
        this.region = new SimpleStringProperty(region);
        this.choc = new SimpleStringProperty(choc);
        this.qualiteIntensiteEpicentre = new SimpleStringProperty(qualiteIntensiteEpicentre);
        this.date = new SimpleStringProperty(calendar.getDateString());
        this.heure = new SimpleStringProperty(calendar.getTimeString());

        // Pour les valeurs string des attributs nombres :
        if (id == null){
            this.stringID = new SimpleStringProperty("");
        }
        else{
            this.stringID = new SimpleStringProperty(String.valueOf(id));
        }

        if (xRGF93 == null){
            this.stringXRGF93 = new SimpleStringProperty("");
        }
        else {
            this.stringXRGF93 = new SimpleStringProperty(String.valueOf(xRGF93));
        }
        if (yRGF93 == null){
            this.stringYRGF93 = new SimpleStringProperty("DEFAULT_INVALID_YRGF93");
        }
        else {
            this.stringYRGF93 = new SimpleStringProperty(String.valueOf(yRGF93));
        }
        if (latitude == null){
            this.stringLatitude = new SimpleStringProperty("");
        }
        else {
            this.stringLatitude = new SimpleStringProperty(String.valueOf(latitude));
        }
        if (longitude == null){
            this.stringLongitude = new SimpleStringProperty("");
        }
        else {
            this.stringLongitude = new SimpleStringProperty(String.valueOf(longitude));
        }
        if (intensite == null){
            this.stringIntensite = new SimpleStringProperty("");
        }
        else {
            this.stringIntensite = new SimpleStringProperty(String.valueOf(intensite));
        }

    }




    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seisme seisme = (Seisme) o;
        return (this.id == seisme.id && this.calendar.equals(seisme.calendar) && this.nom.equals(seisme.nom) &&
                this.region.equals(seisme.region) && this.choc == seisme.choc && this.stringXRGF93 == seisme.stringXRGF93 &&
                this.stringYRGF93 == seisme.stringYRGF93 && this.stringLatitude == seisme.stringLatitude && this.stringLongitude == seisme.stringLongitude &&
                this.stringIntensite == seisme.stringIntensite && this.qualiteIntensiteEpicentre.equals(qualiteIntensiteEpicentre));
    }

    @Override
    public String toString(){
        return "Seisme [id=" + id +
                ", date=" + calendar.getDateString() +
                ", heure=" + calendar.getTimeString() +
                ", nom=" + nom +
                ", region=" + region +
                ", choc=" + choc +
                ", xRGF93=" + stringXRGF93 +
                ", yRGF93=" + stringYRGF93 +
                ", latitude=" + stringLatitude +
                ", logitude=" + stringLongitude +
                ", intensite=" + stringIntensite +
                ", qualiteIntensiteEpicentre=" + qualiteIntensiteEpicentre + "]";

    }

    public static ArrayList<String> getInitialListLabelsAttributs() {
        return initialListLabelsAttributs;
    }

    public StringProperty stringIDProperty() {
        return stringID;
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
        return intensite;
    }

    public StringProperty stringIntensiteProperty() {
        return stringIntensite;
    }

    public Double getLongitude() {
        return longitude;
    }

    public StringProperty stringLongitudeProperty() {
        return stringLongitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public StringProperty stringLatitudeProperty() {
        return stringLatitude;
    }

    public Double getyRGF93() {
        return yRGF93;
    }

    public StringProperty stringYRGF93Property() {
        return stringYRGF93;
    }

    public Double getxRGF93() {
        return xRGF93;
    }

    public StringProperty stringXRGF93Property() {
        return stringXRGF93;
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
        return id;
    }

}

