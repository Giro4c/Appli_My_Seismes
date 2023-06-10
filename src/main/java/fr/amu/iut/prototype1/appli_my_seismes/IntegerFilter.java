package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Une classe représentant un filtre d'appartenance à un intervalle [min, max] sur des Integers.
 * Contient des bind avec les propriétés utilisés lors de son instanciation qui rendent l'objet dynamique sans avoir
 * besoin d'event handlers ou de change listeners.
 */
public class IntegerFilter {

    private StringProperty min = new SimpleStringProperty();
    private StringProperty max = new SimpleStringProperty();

    public String getMax() {
        return max.getValue();
    }

//    public StringProperty maxProperty() {
//        return max;
//    }

    public String getMin() {
        return min.getValue();
    }

//    public StringProperty minProperty() {
//        return min;
//    }

    public IntegerFilter(StringProperty min, StringProperty max){
        this.min.bind(min);
        this.max.bind(max);
    }

    /**
     * Fonction qui renvoie si une valeur Integer se trouve dans l'intervalle [min, max] en considérant que les valeurs
     * contenues dans les String de min et max sont des nombres.
     * Dans le cas où min ou max ne contiennent pas un nombre, considère la condition associée comme valide.
     * @param integer Le nombre (possiblement null) qu'on compare.
     * @return True si integer est dans l'intervalle [min, max]. False s'il n'appartient pas à l'intervalle ou qu'il vaut null (sauf exception min et max valent "").
     */
    public boolean matchFilter(Integer integer){
        if (min.getValue().equals("") && max.getValue().equals("")) return true;
        if (integer != null) {
            boolean matchMin;
            boolean matchMax;
            try {
                matchMin = integer >= Integer.parseInt(min.getValue());
            } catch (Exception e) {
                System.err.println("Erreur: Valeur de filtrage Min non valable");
                matchMin = true;
            }
            try {
                matchMax = integer <= Integer.parseInt(max.getValue());
            } catch (Exception e) {
                System.err.println("Erreur: Valeur de filtrage Max non valable");
                matchMax = true;
            }
            return matchMin && matchMax;
        }
        return false;

    }

}
