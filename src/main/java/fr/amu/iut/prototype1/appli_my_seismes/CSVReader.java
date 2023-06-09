package fr.amu.iut.prototype1.appli_my_seismes;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {

    public static ArrayList<String> CSVFileReader(String csvFileName){
        ArrayList<String> listDonnees = new ArrayList<>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvFileName));
            String line = csvReader.readLine();
            while (line != null){
                listDonnees.add(line.replaceAll("\"", ""));
                line = csvReader.readLine();
            }
            csvReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Le fichier CSV n'a pas été trouvé : " + csvFileName);
            e.printStackTrace(); }
        catch (IOException e) {
            System.out.println("Une erreur est survenue : " + csvFileName);
            e.printStackTrace();
        }
        return listDonnees;
    }
    public static ArrayList<Seisme> StringArrayToSeismeArrayList(ArrayList<String> listDonnees){

        if (listDonnees.size() == 0){
            return null;
        }
        String[] csvLine = listDonnees.get(0).split(",");
        if (csvLine.length == Seisme.COUNT_ATTRIBUTE_LABELS) {
            // Ajout des labels de chaque attribut qui caracterise un séisme
            for (String labelAttribute : csvLine) {
                Seisme.getInitialListLabelsAttributs().add(labelAttribute);
            }
        }

        // Remplissage de la liste de séismes
        ArrayList<Seisme> listSeismes = new ArrayList<>();
        // Declaration de variables de stockage temporaires pour l'instensiation d'un séisme
        Integer id;
        String date;
        String heure;
        String nom;
        String region;
        String choc;
        Double xRGF93;
        Double yRGF93;
        Double latitude;
        Double longitude;
        Double intensite;
        String qualiteIntensite;
        for (int indexSeisme = 1; indexSeisme < listDonnees.size(); ++indexSeisme){
            csvLine = listDonnees.get(indexSeisme).split(",");
            if (csvLine.length != Seisme.COUNT_ATTRIBUTE_LABELS) continue;
            // For the ID attribute
            if (csvLine[0].equals("")){
                id = null;
            }
            else {
                id = Integer.valueOf(csvLine[0]);
            }
            // For the Date attribute
            date = csvLine[1].replaceAll("\"", "");

            // For the Heure attribute
            heure = csvLine[2].replaceAll("\"", "");

            // For the Nom attribute
            nom = csvLine[3].replaceAll("\"", "");

            // For the Region attribute
            region = csvLine[4].replaceAll("\"", "");

            // For the Choc attribute
            choc = csvLine[5].replaceAll("\"", "");

            // For the xRFG93 attribute
            if (csvLine[6].equals("")){
                xRGF93 = null;
            }
            else {
                xRGF93 = Double.valueOf(csvLine[6]);
            }
            // For the yRFG93 attribute
            if (csvLine[7].equals("")){
                yRGF93 = null;
            }
            else {
                yRGF93 = Double.valueOf(csvLine[7]);;
            }
            // For the Latitude attribute
            if (csvLine[8].equals("")){
                latitude = null;
            }
            else {
                latitude = Double.valueOf(csvLine[8]);;
            }
            // For the Longitude attribute
            if (csvLine[9].equals("")){
                longitude = null;
            }
            else {
                longitude = Double.valueOf(csvLine[9]);;
            }
            // For the Intensite attribute
            if (csvLine[10].equals("")){
                intensite = null;
            }
            else {
                intensite = Double.valueOf(csvLine[10]);
            }
            // For the QualiteIntensite attribute
            qualiteIntensite = csvLine[11].replaceAll("\"", "");

            listSeismes.add(new Seisme(id, date, heure, nom, region, choc,
                    xRGF93, yRGF93, latitude, longitude, intensite, qualiteIntensite));

        }

        return listSeismes;
    }



}

