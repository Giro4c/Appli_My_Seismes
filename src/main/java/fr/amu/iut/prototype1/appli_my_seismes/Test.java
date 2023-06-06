package fr.amu.iut.prototype1.appli_my_seismes;

import java.util.Calendar;

public class Test {

    public static void main(String[] args){
        experimentationOnCalender();
    }

    private static void experimentationOnCalender(){
        // Analyse des attributs
        Calendar calendrier = Calendar.getInstance();
        System.out.println(calendrier.getTime());

        for (int i = 0; i < 10; ++i){
            System.out.println("Num field : " + i + ", Value : " + calendrier.get(i));
        }
        System.out.println(calendrier.get(Calendar.MONTH));
        System.out.println(calendrier.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendrier.get(Calendar.YEAR));

        // Après modifications
        calendrier.set(Calendar.DAY_OF_MONTH, 1);
        calendrier.set(Calendar.MONTH, 2);
        calendrier.set(Calendar.YEAR, 2020);
        System.out.println(calendrier.getTime());
        System.out.println(calendrier.get(Calendar.MONTH));
        System.out.println(calendrier.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendrier.get(Calendar.YEAR));
    }

}
