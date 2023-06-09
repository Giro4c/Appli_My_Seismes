package fr.amu.iut.prototype1.appli_my_seismes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.spi.CalendarDataProvider;

public class Test {

    public static void main(String[] args){
        //experimentationOnCalender();
        //testEqualsInteger();
        //testStringSplitForTime();
        //testStringSplitForDate();
        //testCSVReader();

    }

    private static void testCSVReader(){
        ArrayList<String> testCSV = CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_MINI.csv");

//        for (String str : testCSV){
//            System.out.println(str);
//        }

        ArrayList<Seisme> testCSVSeismes = CSVReader.StringArrayToSeismeArrayList(testCSV);

        for (String labelAttributs : Seisme.getInitialListLabelsAttributs()){
            System.out.println(labelAttributs);
        }
        System.out.println("Nombre de label au total : " + Seisme.getInitialListLabelsAttributs().size());
        System.out.println();

        if (testCSVSeismes != null) {
            for (int indexSeisme = 0; indexSeisme < testCSVSeismes.size(); ++indexSeisme) {
                System.out.println(testCSVSeismes.get(indexSeisme).toString());
            }
        }
        else{
            System.out.println("Erreur: le array de devrait pas etre vide pour ce test.");
        }

    }

    private static void showContentStringArray(String[] arrayStr){
        for (String str : arrayStr){
            if (str.equals("")){
                System.out.println("Vide");
            }
            else{
                System.out.println(str);
            }
        }
        System.out.println("Taille : " + arrayStr.length);
        System.out.println();
        System.out.println();
    }

    private static void testStringSplitForTime(){
        String[] split;
        // Test Format : ""
        split = "".split(" ");
        showContentStringArray(split);
        // Test Format : "- h - min - sec"
        split = "13 h 40 min 24 sec".split(" ");
        showContentStringArray(split);
        // Test Format : "- h - min"
        split = "13 h 40 min".split(" ");
        showContentStringArray(split);
        // Test Format : "- h"
        split = "13 h".split(" ");
        showContentStringArray(split);
        // Test Format : "- h - sec"
        split = "13 h 24 sec".split(" ");
        showContentStringArray(split);
        // Test Format : "- sec"
        split = "24 sec".split(" ");
        showContentStringArray(split);
        // Test Format : "- min - sec"
        split = "40 min 24 sec".split(" ");
        showContentStringArray(split);
        // Test Format : "- min"
        split = "40 min".split(" ");
        showContentStringArray(split);

    }

    private static void testStringSplitForDate(){
        String[] split;
        // Test Format : ""
        split = "".split("/");
        showContentStringArray(split);
        // Test Format : AAAA/MM/JJ
        split = "2023/7/5".split("/");
        showContentStringArray(split);
        // Test Format : AAAA/MM/
        split = "2023/7/".split("/");
        showContentStringArray(split);
        // Test Format : AAAA/
        split = "2023/".split("/");
        showContentStringArray(split);
        // Test Format : AAAA//JJ
        split = "2023//5".split("/");
        showContentStringArray(split);
        // Test Format : /JJ
        split = "/5".split("/");
        showContentStringArray(split);
        // Test Format : MM/JJ
        split = "7/5".split("/");
        showContentStringArray(split);
        // Test Format : /MM/
        split = "/7/".split("/");
        showContentStringArray(split);
    }

    private static void testEqualsInteger(){
        Integer int1 = null;
        Integer int2 = Integer.valueOf(1);
        Integer int3 = Integer.valueOf(1);

        System.out.println(int1 == int2);
        System.out.println(int1 != int2);
        System.out.println(int3 == int2);
        System.out.println(int3 != int2);
        //System.out.println(int2.equals(int1));
    }

    private static void experimentationOnCalender(){
        // Analyse des attributs
        Calendar calendrier = Calendar.getInstance();

        System.out.println(calendrier.getTime());

        /*System.out.println(calendrier.get(Calendar.MONTH));
        System.out.println(calendrier.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendrier.get(Calendar.YEAR));
        System.out.println(calendrier.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendrier.get(Calendar.MINUTE));
        System.out.println(calendrier.get(Calendar.SECOND));

        // Après modifications
        calendrier.set(Calendar.DAY_OF_MONTH, 1);
        calendrier.set(Calendar.MONTH, 2);
        calendrier.set(Calendar.YEAR, 2020);
        calendrier.set(Calendar.HOUR_OF_DAY, 12);
        calendrier.set(Calendar.MINUTE, 40);
        calendrier.set(Calendar.SECOND, 3);
        System.out.println(calendrier.getTime());
        System.out.println(calendrier.get(Calendar.MONTH));
        System.out.println(calendrier.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendrier.get(Calendar.YEAR));
        System.out.println(calendrier.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendrier.get(Calendar.MINUTE));
        System.out.println(calendrier.get(Calendar.SECOND));*/

        // Test de dates avec valeurs "manquantes" cad set à 0.
        calendrier.set(Calendar.DAY_OF_MONTH, 0);
        calendrier.set(Calendar.MONTH, 0);
        calendrier.set(Calendar.YEAR, 2020);
        calendrier.set(Calendar.HOUR_OF_DAY, 0);
        calendrier.set(Calendar.MINUTE, 0);
        calendrier.set(Calendar.SECOND, 0);
        System.out.println(calendrier.getTime());
        System.out.println(calendrier.get(Calendar.MONTH));
        System.out.println(calendrier.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendrier.get(Calendar.YEAR));
        System.out.println(calendrier.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendrier.get(Calendar.MINUTE));
        System.out.println(calendrier.get(Calendar.SECOND));



    }

}
