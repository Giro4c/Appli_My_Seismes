package fr.amu.iut.prototype1.appli_my_seismes;

import java.util.ArrayList;
import java.util.Calendar;


public class Seisme {

    private static ArrayList<String> listLabelsAttributs = new ArrayList<>();



    private Integer id;
    private Calendar date;
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

    public Seisme(Integer id, String date, Integer heure, String nom, String region,
                  Integer choc, Double xRGF93, Double yRGF93, Double latitude, Double longitude,
                  Integer intensite, String qualiteIntensiteEpicentre) {
        super();
        this.id = id;
        this.date = Calendar.getInstance();
        this.date.set(Calendar.YEAR, Integer.valueOf(date.split("/")[0]));
        this.date.set(Calendar.MONTH, Integer.valueOf(date.split("/")[1]) - 1);
        this.date.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date.split("/")[2]));
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

    public Calendar getDate() {
        return date;
    }
    public String getDateString() {
        return "" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH);
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
    public void setDate(String date) {
        if (date.split("/").length == 3) {
            this.date.set(Calendar.YEAR, Integer.valueOf(date.split("/")[0]));
            this.date.set(Calendar.MONTH, Integer.valueOf(date.split("/")[1]) - 1);
            this.date.set(Calendar.DAY_OF_MONTH, Integer.valueOf(date.split("/")[2]));
        }
        else {
            System.err.println("Error: Incorrect Date String format.");
        }
    }
    public void setDate(int year, int month, int day) {
        if (year > 0 && month > 0 && day > 0) {
            this.date.set(Calendar.YEAR, year);
            this.date.set(Calendar.MONTH, month - 1);
            this.date.set(Calendar.DAY_OF_MONTH, day);
        }
        else {
            System.err.println("Error: Incorrect Date Value format.");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

