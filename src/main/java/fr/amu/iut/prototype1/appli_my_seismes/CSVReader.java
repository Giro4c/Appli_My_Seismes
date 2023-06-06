package fr.amu.iut.prototype1.appli_my_seismes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CSVReader {

    public static ArrayList<String> CSVFileReader(String csvFileName){
        ArrayList<String> listDonnees = new ArrayList<>();
        try {
            File csvFile = new File(csvFileName);
            Scanner csvScanner = new Scanner(csvFile);
            while (csvScanner.hasNextLine()){
                listDonnees.add(csvScanner.nextLine());
            }
            csvScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Une erreur est survenue : Impossible de trouver le fichier.");
            e.printStackTrace();
        }
        return listDonnees;
    }
    public static ArrayList<Seisme> StringArrayToSeismeArrayList(ArrayList<String> listDonnees){

        if (listDonnees.size() == 0){
            return null;
        }
        String[] csvLine = listDonnees.get(0).split(",");
        // Ajout des labels de chaque attribut qui caracterise un séisme
        for (String labelAttribute : csvLine){
            Seisme.getListLabelsAttributs().add(labelAttribute);
        }
        // Remplissage de la liste de séismes
        ArrayList<Seisme> listSeismes = new ArrayList<>();
        // Declaration de variables de stockage temporaires pour l'instensiation d'un séisme
        Integer id;
        String date;
        Integer heure;
        String nom;
        String region;
        Integer choc;
        Double xRGF93;
        Double yRGF93;
        Double latitude;
        Double longitude;
        Integer intensite;
        String qualiteIntensite;
        for (int indexSeisme = 1; indexSeisme < listDonnees.size(); ++indexSeisme){
            csvLine = listDonnees.get(indexSeisme).split(",");
            // For the ID attribute
            if (csvLine[0] == null || csvLine[0].equals("")){
                id = null;
            }
            else {
                id = Integer.valueOf(csvLine[0]);
            }
            // For the Date attribute
            if (csvLine[1] == null || csvLine[1].equals("")){
                date = null;
            }
            else {
                date = csvLine[1];
            }
            // For the Heure attribute
            if (csvLine[2] == null || csvLine[2].equals("")){
                heure = null;
            }
            else {
                heure = Integer.valueOf(csvLine[2]);
            }
            // For the Nom attribute
            if (csvLine[3] == null || csvLine[3].equals("")){
                nom = null;
            }
            else {
                nom = csvLine[3];
            }
            // For the Region attribute
            if (csvLine[4] == null || csvLine[4].equals("")){
                region = null;
            }
            else {
                region = csvLine[4];
            }
            // For the Choc attribute
            if (csvLine[5] == null || csvLine[5].equals("")){
                choc = null;
            }
            else {
                choc = Integer.valueOf(csvLine[5]);
            }
            // For the xRFG93 attribute
            if (csvLine[6] == null || csvLine[6].equals("")){
                xRGF93 = null;
            }
            else {
                xRGF93 = Double.valueOf(csvLine[6]);
            }
            // For the yRFG93 attribute
            if (csvLine[7] == null || csvLine[7].equals("")){
                yRGF93 = null;
            }
            else {
                yRGF93 = Double.valueOf(csvLine[7]);;
            }
            // For the Latitude attribute
            if (csvLine[8] == null || csvLine[8].equals("")){
                latitude = null;
            }
            else {
                latitude = Double.valueOf(csvLine[8]);;
            }
            // For the Longitude attribute
            if (csvLine[9] == null || csvLine[9].equals("")){
                longitude = null;
            }
            else {
                longitude = Double.valueOf(csvLine[9]);;
            }
            // For the Intensite attribute
            if (csvLine[10] == null || csvLine[10].equals("")){
                intensite = null;
            }
            else {
                intensite = Integer.valueOf(csvLine[10]);
            }
            // For the QualiteIntensite attribute
            if (csvLine[11] == null || csvLine[11].equals("")){
                qualiteIntensite = null;
            }
            else {
                qualiteIntensite = csvLine[11];
            }

            listSeismes.add(new Seisme(id, date, heure, nom, region, choc,
                    xRGF93, yRGF93, latitude, longitude, intensite, qualiteIntensite));


        }

        return listSeismes;
    }



}

