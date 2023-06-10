package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Une classe représentant un filtre d'appartenance à un intervalle [min, max] sur des Doubles.
 * Contient des bind avec les propriétés utilisés lors de son instanciation qui rendent l'objet dynamique sans avoir
 * besoin d'event handlers ou de change listeners.
 */
public class DoubleFilter {

    private StringProperty min = new SimpleStringProperty();
    private StringProperty max = new SimpleStringProperty();

    public String getMax() {
        return max.getValue();
    }

    public String getMin() {
        return min.getValue();
    }

    public DoubleFilter(StringProperty min, StringProperty max){
        this.min.bind(min);
        this.max.bind(max);
    }

    /**
     * Fonction qui renvoie si une valeur Double se trouve dans l'intervalle [min, max] en considérant que les valeurs
     * contenues dans les String de min et max sont des nombres décimaux.
     * Dans le cas où min ou max ne contiennent pas un nombre, considère la condition associée comme valide.
     * @param doubleVal Le nombre (possiblement null) qu'on compare.
     * @return True si doubleVal est dans l'intervalle [min, max]. False s'il n'appartient pas à l'intervalle ou qu'il vaut null (sauf exception min et max valent "").
     */
    public boolean matchFilter(Double doubleVal){
        if (min.getValue().equals("") && max.getValue().equals("")) return true;
        if (doubleVal != null) {
            boolean matchMin;
            boolean matchMax;
            try {
                matchMin = doubleVal >= Double.parseDouble(min.getValue().replaceAll(",", "."));
            } catch (Exception e) {
                System.err.println("Erreur: Valeur de filtrage Min non valable");
                matchMin = true;
            }
            try {
                matchMax = doubleVal <= Double.parseDouble(max.getValue().replaceAll(",", "."));
            } catch (Exception e) {
                System.err.println("Erreur: Valeur de filtrage Max non valable");
                matchMax = true;
            }
            return matchMin && matchMax;
        }
        return false;

    }

}
