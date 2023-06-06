package fr.amu.iut.prototype1.appli_my_seismes;

import java.util.ArrayList;


public class Seisme {

    private static ArrayList<String> listLabelsAttributs = new ArrayList<>();



    private Integer id;
    private Date date;
    private Integer heure;
    private String nom;
    private String region;
    private Integer choc;
    private Double xRGF93;
    private Double yRGF93;
    private Double latitude;
    private Double longitude;
    private Integer intensite;
    private String qualiteIntensiteEpicentre;

    public Seisme(Integer id, Date date, Integer heure, String nom, String region,
                  Integer choc, Double xRGF93, Double yRGF93, Double latitude, Double longitude,
                  Integer intensite, String qualiteIntensiteEpicentre) {
        super();
        this.id = id;
        this.date = date;
        this.heure = heure;
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

    public static ArrayList<String> getListLabelsAttributs() {
        return listLabelsAttributs;
    }

    public String getQualiteIntensiteEpicentre() {
        return qualiteIntensiteEpicentre;
    }

    public void setQualiteIntensiteEpicentre(String qualiteIntensiteEpicentre) {
        this.qualiteIntensiteEpicentre = qualiteIntensiteEpicentre;
    }

    public Integer getIntensite() {
        return intensite;
    }

    public void setIntensite(Integer intensite) {
        this.intensite = intensite;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getyRGF93() {
        return yRGF93;
    }

    public void setyRGF93(Double yRGF93) {
        this.yRGF93 = yRGF93;
    }

    public Double getxRGF93() {
        return xRGF93;
    }

    public void setxRGF93(Double xRGF93) {
        this.xRGF93 = xRGF93;
    }

    public Integer getChoc() {
        return choc;
    }

    public void setChoc(Integer choc) {
        this.choc = choc;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getHeure() {
        return heure;
    }

    public void setHeure(Integer heure) {
        this.heure = heure;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

