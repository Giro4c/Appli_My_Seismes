package fr.amu.iut.prototype1.appli_my_seismes;

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
    private static ArrayList<String> initialListLabelsAttributs = new ArrayList<>();



    private Integer id;
    private CalendarWithNulls calendar;
    private String nom;
    private String region;
    private String choc;
    private Double xRGF93;
    private Double yRGF93;
    private Double latitude;
    private Double longitude;
    private Double intensite;
    private String qualiteIntensiteEpicentre;

    public Seisme(Integer id, String date, String heure, String nom, String region,
                  String choc, Double xRGF93, Double yRGF93, Double latitude, Double longitude,
                  Double intensite, String qualiteIntensiteEpicentre) {
        super();
        this.id = id;
        this.calendar = new CalendarWithNulls(date, heure);
        this.nom = nom;
        this.region = region;
        this.choc = choc;
        this.xRGF93 = xRGF93;
        this.yRGF93 = yRGF93;
        this.latitude = latitude;
        this.longitude = longitude;
        this.intensite = intensite;
        this.qualiteIntensiteEpicentre = qualiteIntensiteEpicentre;

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

    public String getQualiteIntensiteEpicentre() {
        return qualiteIntensiteEpicentre;
    }

    /*public void setQualiteIntensiteEpicentre(String qualiteIntensiteEpicentre) {
        this.qualiteIntensiteEpicentre = qualiteIntensiteEpicentre;
    }*/

    public Double getIntensite() {
        return intensite;
    }

    /*public void setIntensite(Integer intensite) {
        this.intensite = intensite;
    }*/

    public Double getLongitude() {
        return longitude;
    }

    /*public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }*/

    public Double getLatitude() {
        return latitude;
    }

    /*public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }*/

    public Double getyRGF93() {
        return yRGF93;
    }

    /*public void setyRGF93(Double yRGF93) {
        this.yRGF93 = yRGF93;
    }*/

    public Double getxRGF93() {
        return xRGF93;
    }

    /*public void setxRGF93(Double xRGF93) {
        this.xRGF93 = xRGF93;
    }*/

    public String getChoc() {
        return choc;
    }

    /*public void setChoc(Integer choc) {
        this.choc = choc;
    }*/

    public String getRegion() {
        return region;
    }

    /*public void setRegion(String region) {
        this.region = region;
    }*/

    public String getNom() {
        return nom;
    }

    /*public void setNom(String nom) {
        this.nom = nom;
    }*/

    public CalendarWithNulls getCalendar() {
        return calendar;
    }

    public Integer getId() {
        return id;
    }

    /*public void setId(Integer id) {
        this.id = id;
    }*/
}

