package fr.amu.iut.prototype1.appli_my_seismes.datas.filters;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Une classe représentant un filtre de correspondance à des critères sur des String :
 * Commence par une certaine String de début, se termine par une certaine String de fin, et contient une String spécifique.
 * Contient des bind avec les propriétés utilisés lors de son instanciation qui rendent l'objet dynamique sans avoir
 * besoin d'event handlers ou de change listeners.
 */
public class StringFilter {

    private StringProperty debut = new SimpleStringProperty();
    private StringProperty fin = new SimpleStringProperty();
    private StringProperty contained = new SimpleStringProperty();

    public String getDebut() {
        return debut.getValue();
    }

    public String getFin() {
        return fin.getValue();
    }

    public String getContained() {
        return contained.getValue();
    }

    public StringFilter(StringProperty debut, StringProperty fin, StringProperty contained){
        this.debut.bind(debut);
        this.fin.bind(fin);
        this.contained.bind(contained);
    }

    /**
     * Fonction qui renvoie si une String correspond aux critères du filtre :
     * Commence par une certaine String de début, se termine par une certaine String de fin, et contient une String spécifique.
     * Dans le cas où debut, fin ou contained ne contiennent pas de caractères, considère la condition associée comme valide.
     * @param string La String (possiblement null) qu'on compare.
     * @return True si la String correspond aux critères. False si elle ne correspond pas ou qu'elle vaut null (sauf exception debut, fin et contained valent "").
     */
    public boolean matchFilter(String string){
        return matchDebut(string) && matchFin(string) && matchContained(string);
    }

    /**
     * Fonction qui renvoie si une String correspond aux critères du filtre : Commence par une certaine String de début.
     * Dans le cas où debut ne contient pas de caractères, considère la condition comme valide.
     * @param string La String (possiblement null) qu'on compare.
     * @return True si la String commence par debut. False si elle ne correspond pas ou qu'elle vaut null (sauf exception debut vaut "").
     */
    public boolean matchDebut(String string){
        if (debut.getValue().equals("")) return true;
        if (string == null) return false;
        if (string.length() < debut.getValue().length()) return false;
        string = string.toLowerCase();
        for (int indexChar = 0; indexChar < debut.getValue().length(); ++indexChar){
            if (string.charAt(indexChar) != debut.getValue().toLowerCase().charAt(indexChar)) return false;
        }
        return true;
    }

    /**
     * Fonction qui renvoie si une String correspond aux critères du filtre : Se termine par une certaine String de fin.
     * Dans le cas où fin ne contient pas de caractères, considère la condition comme valide.
     * @param string La String (possiblement null) qu'on compare.
     * @return True si la String se termine par fin. False si elle ne correspond pas ou qu'elle vaut null (sauf exception fin vaut "").
     */
    public boolean matchFin(String string){
        if (fin.getValue().equals("")) return true;
        if (string == null) return false;
        if (string.length() < fin.getValue().length()) return false;
        string = string.toLowerCase();
        for (int indexChar = 0; indexChar < fin.getValue().length(); ++indexChar){
            if (string.charAt(string.length() - indexChar - 1) != fin.getValue().toLowerCase().charAt(fin.getValue().length() - indexChar -1)) return false;
        }
        return true;
    }

    /**
     * Fonction qui renvoie si une String correspond aux critères du filtre : Contient une String spécifique.
     * Dans le cas où contained ne contient pas de caractères, considère la condition comme valide.
     * @param string La String (possiblement null) qu'on compare.
     * @return True si la String contient contained. False si elle ne correspond pas ou qu'elle vaut null (sauf exception contained vaut "").
     */
    public boolean matchContained(String string){
        if (contained.getValue().equals("")) return true;
        if (string == null) return false;
        string = string.toLowerCase();
        return string.contains(contained.getValue().toLowerCase());
    }


}
