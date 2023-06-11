package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.scene.paint.Color;

/**
 * Classe contenant des méthodes statiques diverses pouvant être réutilisées par les autres classes du projet.
 */
public class Utilitaire {

    /**
     * Détermine la couleur du spectre rouge associée à un entier sur l'échelle de Richter représentée par l'intervalle [0 ; 10].
     * Dans le cas où l'entier n'est pas dans cet intervalle, détermine la couleur associée à la borne la plus proche.
     * @param intensite Enitier repésentant l'intensité d'un séisme sur l'échelle de Richter
     * @return Color sur le spectre du rouge (allant de Jaune vers Rouge) sur l'échelle de Richter.
     */
    public static Color richterColor(double intensite){
        if (intensite < 0) return richterColor(0);
        if (intensite > 10) return richterColor(10);

        int rouge = 255;
        int vert = 255 - (int)(24.2 * intensite);
        int bleu = 13;
        return Color.rgb(rouge, vert, bleu);
    }

}
