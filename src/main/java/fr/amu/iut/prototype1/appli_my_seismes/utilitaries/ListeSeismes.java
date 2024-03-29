package fr.amu.iut.prototype1.appli_my_seismes.utilitaries;

import fr.amu.iut.prototype1.appli_my_seismes.datas.Seisme;

import java.util.ArrayList;

/**
 * Classe contenant des fonctions statiques renvoyant des listes de Séismes pour les tests d'affichage et autres.
 * @deprecated Nouvelles listes issues de CSVReader sont disponibles. Remplacent les listes test de cette classe.
 * @see CSVReader
 */
public class ListeSeismes {
    public static ArrayList<Seisme> listeTestSeismes() {
        ArrayList<Seisme> listeSeisme = new ArrayList();

        //initialisation des tests seismes
        Seisme seisme = new Seisme(1, "2020/12/9","10 h", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 7.0, "Bonne");
        Seisme seisme2 = new Seisme(2, "2021/12/9","10 min", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 7.0, "Bonne");
        Seisme seisme3 = new Seisme(3, "2022/12/9","10 sec", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 7.0, "Bonne");
        Seisme seisme4 = new Seisme(4, "2023/12/9","1 h 30 min", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 7.0, "Bonne");
        Seisme seisme5 = new Seisme(5, "2019/12/9","10 h 52 sec", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 8.0, "Bonne");
        Seisme seisme6 = new Seisme(6, "2025/12/9","1 h 25 min 1 sec", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 7.0, "Bonne");
        Seisme seisme7 = new Seisme(7, "2026/12/9","10 h", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 6.5, "Bonne");
        Seisme seisme8 = new Seisme(8, "2027/12/9","10 h", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 5.1, "Bonne");
        Seisme seisme9 = new Seisme(9, "2028/12/9","11 h", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 2.0, "Bonne");
        Seisme seisme10 = new Seisme(10, "2029/12/9","10 h 20 min", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 4.0, "Bonne");
        Seisme seisme11 = new Seisme(11, "2030/12/9","13 min 30 sec", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 10.0, "Bonne");
        Seisme seisme12 = new Seisme(12, "2031/12/9","0 h 12 min", "Seisme1", "PACA", "FAIBLE", 123.45, 678.90,
                12.34, 56.78, 3.5, "Bonne");
        //ajout des seismes tests à la liste
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


        //affichage de la liste
//        for (int i=0;i<listeSeisme.size();++i) {
//            System.out.println("ID : " + listeSeisme.get(i).getId());
//            System.out.println("Date : " + listeSeisme.get(i).getDate().getTime());
//            System.out.println("Heure : " + listeSeisme.get(i).getHeure());
//            System.out.println("Nom : " + listeSeisme.get(i).getNom());
//            System.out.println("Région : " +listeSeisme.get(i).getRegion());
//            System.out.println("Choc : " + listeSeisme.get(i).getChoc());
//            System.out.println("X : " + listeSeisme.get(i).getxRGF93());
//            System.out.println("Y : " + listeSeisme.get(i).getyRGF93());
//            System.out.println("Latitude : " + listeSeisme.get(i).getLatitude());
//            System.out.println("Longitude : " + listeSeisme.get(i).getLongitude());
//            System.out.println("Intensite : " + listeSeisme.get(i).getIntensite());
//            System.out.println("IntensiteEpicentre : " + listeSeisme.get(i).getQualiteIntensiteEpicentre());
//            System.out.println("-----------------------");
//        }


        // Accès à la liste des labels d'attributs statique de la classe Seisme
        ArrayList<String> labelsAttributs = Seisme.getInitialListLabelsAttributs();

//        // Affichage des labels des attributs
//        for (String label : labelsAttributs) {
//            System.out.println("Label attribut: " + label);
//        }

        return listeSeisme;
    }
}
