package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.*;

import java.time.LocalDate;

/**
 * Une classe représentant un filtre de correspondance à des critères sur des CalendarWithNulls :
 * Date comprise entre deux dates minDate et maxDate, Heure comprise entre deux heures minTime et maxTime
 * Contient des bind avec les propriétés utilisés lors de son instanciation qui rendent l'objet dynamique sans avoir
 * besoin d'event handlers ou de change listeners.
 * <em>CalendarWithNulls ayant des getter particuliers mais pas de properties String liées à ceux-ci, des change listeners
 * sont en place pour faire des modifications automatiques sur les valeurs contenues dans ces calendars quand les critères
 * de filtres changent.</em>
 */
public class CalendarFilter {

    private CalendarWithNulls minCalendar = new CalendarWithNulls();
    private CalendarWithNulls maxCalendar = new CalendarWithNulls();

    private StringProperty minAnnee = new SimpleStringProperty();
    private StringProperty maxAnnee = new SimpleStringProperty();
    private StringProperty minMois = new SimpleStringProperty();
    private StringProperty maxMois = new SimpleStringProperty();
    private StringProperty minJour = new SimpleStringProperty();
    private StringProperty maxJour = new SimpleStringProperty();
    private IntegerProperty minHeure = new SimpleIntegerProperty();
    private IntegerProperty maxHeure = new SimpleIntegerProperty();
    private IntegerProperty minMinute = new SimpleIntegerProperty();
    private IntegerProperty maxMinute = new SimpleIntegerProperty();
    private IntegerProperty minSeconde = new SimpleIntegerProperty();
    private IntegerProperty maxSeconde = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> exactDate;

    public CalendarFilter(StringProperty minAnnee, StringProperty maxAnnee, StringProperty minMois, StringProperty maxMois,
                          StringProperty minJour, StringProperty maxJour, IntegerProperty minHeure, IntegerProperty maxHeure,
                          IntegerProperty minMinute, IntegerProperty maxMinute, IntegerProperty minSeconde,
                          IntegerProperty maxSeconde){

        bindingsMinCalendar();
        bindingsMaxCalendar();

        this.minAnnee.bind(minAnnee);
        this.maxAnnee.bind(maxAnnee);
        this.minMois.bind(minMois);
        this.maxMois.bind(maxMois);
        this.minJour.bind(minJour);
        this.maxJour.bind(maxJour);
        this.minHeure.bind(minHeure);
        this.maxHeure.bind(maxHeure);
        this.minMinute.bind(minMinute);
        this.maxMinute.bind(maxMinute);
        this.minSeconde.bind(minSeconde);
        this.maxSeconde.bind(maxSeconde);

    }


    /**
     * Fonction qui renvoie si deux Strings stringDate et stringTime correspondent aux critères du filtre :
     * Date associée comprise entre deux dates minDate et maxDate, Heure associée comprise entre deux heures minTime et maxTime.
     * Dans le cas où les attributs de filtre ne contiennent pas de nombres, considère la condition associée comme valide.
     * @param stringDate La String (possiblement null) qu'on compare aux dates des CalenderWithNulls.
     * @param stringTime La String (possiblement null) qu'on compare aux heures des CalenderWithNulls.
     * @return True si les Strings correspondent aux critères. False si elles ne correspondent pas ou qu'elles vallent null (sauf exception dates et times valent "").
     */
    public boolean matchFilter(String stringDate, String stringTime){
        return matchDateFilter(stringDate) && matchTimeFilter(stringTime);
    }

    /**
     * Fonction qui renvoie si une String stringDate correspond aux critères du filtre :
     * Date associée comprise entre deux dates minDate et maxDate.
     * Dans le cas où les attributs de filtre ne contiennent pas de nombres, considère la condition associée comme valide.
     * @param stringDate La String (possiblement null) qu'on compare aux dates des CalenderWithNulls.
     * @return True si la Strings correspond aux critères. False si elle ne correspond pas ou qu'elle vaut null (sauf exception dates valent "").
     */
    public boolean matchDateFilter(String stringDate){

        if (stringDate == null) return false;
        return minCalendar.isDateInferiorOrEquals(stringDate) && maxCalendar.isDateSuperiorOrEquals(stringDate);

    }

    /**
     * Fonction qui renvoie si une String stringDate correspond aux critères du filtre :
     * Heure associée comprise entre deux heures minTime et maxTime.
     * Dans le cas où les attributs de filtre ne contiennent pas de nombres, considère la condition associée comme valide.
     * @param stringTime La String (possiblement null) qu'on compare aux heures des CalenderWithNulls.
     * @return True si la Strings correspond aux critères. False si elle ne correspond pas ou qu'elle vaut null (sauf exception times valent "").
     */
    public boolean matchTimeFilter(String stringTime){

        if (stringTime == null) return false;
        return minCalendar.isTimeInferiorOrEquals(stringTime) && maxCalendar.isTimeSuperiorOrEquals(stringTime);

    }

    /**
     * Fonction qui établit des liens de synchronisation entre les propriétés de la date minimale (minAnnee, minMois, minJour, minHeure, minMinute, minSeconde)
     * et l'objet "minCalendar" représentant la date minimale.
     * Lorsque les propriétés changent, les écouteurs correspondants mettent à jour les valeurs appropriées de "minCalendar".
     * Les valeurs sont converties et affectées aux champs correspondants de "minCalendar".
     * En cas d'erreur lors de la conversion ou de l'affectation, les champs correspondants de "minCalendar" sont définis sur "null".
     */
    private void bindingsMinCalendar(){
        minAnnee.addListener((observableValue, s, t1) -> {
            try{
                minCalendar.setAnnee(Integer.valueOf(observableValue.getValue()));
            } catch (Exception e){
                minCalendar.setAnnee(null);
            }
        });
        minMois.addListener((observableValue, s, t1) -> {
            try{
                minCalendar.setMois(Integer.valueOf(observableValue.getValue()) -1);
            } catch (Exception e){
                minCalendar.setMois(null);
            }
        });
        minJour.addListener((observableValue, s, t1) -> {
            try{
                minCalendar.setJour(Integer.valueOf(observableValue.getValue()));
            } catch (Exception e){
                minCalendar.setJour(null);
            }
        });
        minHeure.addListener(observable -> {
            try{
                minCalendar.setHeure(minHeure.getValue());
            } catch (Exception e){
                minCalendar.setHeure(null);
            }
        });
        minMinute.addListener(observable -> {
            try{
                minCalendar.setMinute(minMinute.getValue());
            } catch (Exception e){
                minCalendar.setMinute(null);
            }
        });
        minSeconde.addListener(observable -> {
            try{
                minCalendar.setSeconde(minSeconde.getValue());
            } catch (Exception e){
                minCalendar.setSeconde(null);
            }
        });
    }


    /**
     * Fonction qui établit des liens de synchronisation entre les propriétés de la date maximale (maxAnnee, maxMois, maxJour, maxHeure, maxMinute, maxSeconde)
     * et l'objet "maxCalendar" représentant la date maximale.
     * Lorsque les propriétés changent, les écouteurs correspondants mettent à jour les valeurs appropriées de "maxCalendar".
     * Les valeurs sont converties et affectées aux champs correspondants de "maxCalendar".
     * En cas d'erreur lors de la conversion ou de l'affectation, les champs correspondants de "maxCalendar" sont définis sur "null".
     */
    private void bindingsMaxCalendar(){
        maxAnnee.addListener((observableValue, s, t1) -> {
            try{
                maxCalendar.setAnnee(Integer.valueOf(observableValue.getValue()));
            } catch (Exception e){
                maxCalendar.setAnnee(null);
            }
        });
        maxMois.addListener((observableValue, s, t1) -> {
            try{
                maxCalendar.setMois(Integer.valueOf(observableValue.getValue()) -1);
            } catch (Exception e){
                maxCalendar.setMois(null);
            }
        });
        maxJour.addListener((observableValue, s, t1) -> {
            try{
                maxCalendar.setJour(Integer.valueOf(observableValue.getValue()));
            } catch (Exception e){
                maxCalendar.setJour(null);
            }
        });
        maxHeure.addListener(observable -> {
            try{
                maxCalendar.setHeure(maxHeure.getValue());
            } catch (Exception e){
                maxCalendar.setHeure(null);
            }
        });
        maxMinute.addListener(observable -> {
            try{
                maxCalendar.setMinute(maxMinute.getValue());
            } catch (Exception e){
                maxCalendar.setMinute(null);
            }
        });
        maxSeconde.addListener(observable -> {
            try{
                maxCalendar.setSeconde(maxSeconde.getValue());
            } catch (Exception e){
                maxCalendar.setSeconde(null);
            }
        });
    }

    public CalendarWithNulls getMinCalendar() {
        return minCalendar;
    }

    public CalendarWithNulls getMaxCalendar() {
        return maxCalendar;
    }

    public int getMaxSeconde() {
        return maxSeconde.get();
    }

    public int getMinSeconde() {
        return minSeconde.get();
    }

    public int getMaxMinute() {
        return maxMinute.get();
    }

    public int getMinMinute() {
        return minMinute.get();
    }

    public int getMaxHeure() {
        return maxHeure.get();
    }

    public int getMinHeure() {
        return minHeure.get();
    }

    public String getMaxJour() {
        return maxJour.get();
    }

    public String getMinJour() {
        return minJour.get();
    }

    public String getMaxMois() {
        return maxMois.get();
    }

    public String getMinMois() {
        return minMois.get();
    }

    public String getMaxAnnee() {
        return maxAnnee.get();
    }

    public String getMinAnnee() {
        return minAnnee.get();
    }

}
