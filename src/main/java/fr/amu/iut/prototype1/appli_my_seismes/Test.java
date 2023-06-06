package fr.amu.iut.prototype1.appli_my_seismes;

import java.util.Calendar;
import java.util.spi.CalendarDataProvider;

public class Test {

    public static void main(String[] args){
        //experimentationOnCalender();
        testEqualsInteger();
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
