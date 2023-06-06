package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;

public class AfficheurDonnees extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création d'un objet Seisme


        // Accès à la liste des labels d'attributs statique de la classe Seisme
        ArrayList<String> labelsAttributs = Seisme.getListLabelsAttributs();

        // Affichage des labels des attributs
        for (String label : labelsAttributs) {
            System.out.println("Label attribut: " + label);
        }

    }

    ArrayList<Seisme> listeTestSeismes() {
        ArrayList listeSeisme = new ArrayList();


        Seisme seisme = new Seisme(1, 2020,12,9,10, "Seisme1", "PACA", 1, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme2 = new Seisme(2, new Date(2023,11,5),7, "Nom du séismePATRICK", "Région", 5, 1.0, 1.0,
                1.0, 1.0, 1, "Bonne");
        Seisme seisme3 = new Seisme(3, new Date(2019,3,4),2, "Nom du séisme", "Région", 2, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme4 = new Seisme(4, new Date(2024,4,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme5 = new Seisme(5, new Date(2021,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme6 = new Seisme(6, new Date(2012,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme7 = new Seisme(7, new Date(2014,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme8 = new Seisme(8, new Date(1999,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme9 = new Seisme(9, new Date(1990,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme10 = new Seisme(10, new Date(1980,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme11 = new Seisme(11, new Date(1933,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");
        Seisme seisme12 = new Seisme(12, new Date(1930,12,9),12, "Nom du séisme", "Région", 5, 123.45, 678.90,
                12.34, 56.78, 7, "Bonne");

        listeSeisme.add(seisme);
        listeSeisme.add(seisme2);
        listeSeisme.add(seisme3);
        listeSeisme.add(seisme4);
        listeSeisme.add(seisme5);
        listeSeisme.add(seisme6);
        listeSeisme.add(seisme7);
        listeSeisme.add(seisme8);
        listeSeisme.add(seisme9);
        listeSeisme.add(seisme10);
        listeSeisme.add(seisme11);
        listeSeisme.add(seisme12);

        for(int i=0;i<listeSeisme.size()-1;++i) {

        }

             /*
        Integer id = seisme.getId();
        Date date = seisme.getDate();
        Integer heure = seisme.getHeure();
        String nom = seisme.getNom();
        String region = seisme.getRegion();
        Integer choc = seisme.getChoc();
        Double xRGF93 = seisme.getxRGF93();
        Double yRGF93 = seisme.getyRGF93();
        Double latitude = seisme.getLatitude();
        Double longitude = seisme.getLongitude();
        Integer intensite = seisme.getIntensite();
        String qualiteIntensiteEpicentre = seisme.getQualiteIntensiteEpicentre();

        System.out.println("ID: " + id);
        System.out.println("Date: " + date);
        System.out.println("Heure: " + heure);
        System.out.println("Nom: " + nom);
        System.out.println("Région: " + region);
        System.out.println("Choc: " + choc);
        System.out.println("xRGF93: " + xRGF93);
        System.out.println("yRGF93: " + yRGF93);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Intensité: " + intensite);
        System.out.println("Qualité intensité épicentre: " + qualiteIntensiteEpicentre);*/

        return listeSeisme;
    }
}

