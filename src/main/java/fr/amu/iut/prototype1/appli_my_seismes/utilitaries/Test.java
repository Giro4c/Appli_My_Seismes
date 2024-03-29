package fr.amu.iut.prototype1.appli_my_seismes.utilitaries;

import fr.amu.iut.prototype1.appli_my_seismes.datas.Seisme;
import fr.amu.iut.prototype1.appli_my_seismes.utilitaries.CSVReader;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Un classe de tests pour expériementer avec divers notions sans endommager le code.
 */
public class Test {

    public static void main(String[] args){
        //experimentationOnCalender();
        //testEqualsInteger();
        //testStringSplitForTime();
        //testStringSplitForDate();
        //testCSVReader();
        //testEquals();
        testImageViewSrc();

    }

    private static void testImageViewSrc(){
//        ImageView imageView = new ImageView("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("main/resources/fr/amu/iut/prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("resources/fr/amu/iut/prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("fr/amu/iut/prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("amu/iut/prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("iut/prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("prototype1/appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("appli_my_seismes/imgHome.png");
//        ImageView imageView = new ImageView("imgHome.png");

//        ImageView imageView = new ImageView();
//        imageView.setImage(new Image(Test.class.getResource("imgHome.png").toString()));


    }

    private static void testEquals(){

        // String
//        String str1 = "";
//        String str2 = "";
//        System.out.println(str1.equals(""));
//        System.out.println(str2.equals(""));
//        System.out.println(str1.equals(str2));

//        StringProperty strProp1 = new SimpleStringProperty("");
//        StringProperty strProp2 = new SimpleStringProperty("");
//        System.out.println(strProp1.equals(""));
//        System.out.println(strProp2.equals(""));
//        System.out.println(strProp1.equals(strProp2));

//        System.out.println(strProp1.getValue().equals(""));
//        System.out.println(strProp2.getValue().equals(""));
//        System.out.println(strProp1.getValue().equals(strProp2.getValue()));

        // Integer
        Integer integer1 = null;
        Integer integer2 = null;
        Integer integer3 = Integer.valueOf(1);
        Integer integer4 = Integer.valueOf(1);

//        System.out.println(integer1 == integer2);
//        System.out.println(integer1 == integer3);
//        System.out.println(integer3.equals(integer1));

        System.out.println(testBool1(integer3, integer4));
        System.out.println(testBool2(integer3, integer4));
        System.out.println();
        integer4 = Integer.valueOf(4);
        System.out.println(testBool1(integer3, integer4));
        System.out.println(testBool2(integer3, integer4));
        System.out.println();
        integer4 = null;
        System.out.println(testBool1(integer3, integer4));
        System.out.println(testBool2(integer3, integer4));
        System.out.println();
        integer4 = Integer.valueOf(4);
        integer3 = null;
        System.out.println(testBool1(integer3, integer4));
        System.out.println(testBool2(integer3, integer4));
        System.out.println();
        integer4 = null;
        System.out.println(testBool1(integer3, integer4));
        System.out.println(testBool2(integer3, integer4));
        System.out.println();



    }

    private static boolean testBool1(Integer integer1, Integer integer2){
        if (integer1 != null){
            if (!integer1.equals(integer2)){
                return false;
            }
        }
        else if (integer2 != null){
            return false;
        }
        return true;
    }
    private static boolean testBool2(Integer integer1, Integer integer2){
        if ((integer1 != null && !integer1.equals(integer2))
                || (integer1 == null && integer2 != null)){
            return false;
        }
        return true;
    }

    private static void testObservables(){
        ObservableValue<String> obsString = new SimpleStringProperty();
        System.out.println(obsString.getValue());
        ObservableValue<Boolean> obsBoolean = new SimpleBooleanProperty();
    }

    private static void testCSVReader(){
        ArrayList<String> testCSV = null;
        try {
            testCSV = CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_MINI.csv");
        } catch (FileNotFoundException e) {
            testCSV = new ArrayList<>();
        }

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
            System.out.println("Erreur: le array ne devrait pas etre vide pour ce test.");
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
        System.out.println(Integer.valueOf(0));
        Integer int0 = Integer.valueOf(0);
        System.out.print(int0);

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
        System.out.println();
        calendrier.set(Calendar.YEAR, 1);
        System.out.println(calendrier.get(Calendar.YEAR));

    }

}
