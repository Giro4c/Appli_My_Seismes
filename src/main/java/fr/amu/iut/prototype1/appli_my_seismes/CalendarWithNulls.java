package fr.amu.iut.prototype1.appli_my_seismes;

import java.util.Calendar;

/**
 * <p>
 * Une classe semblable à celle d'un calendrier normal à l'exeption que
 * des valeurs peuvent ne pas être determinées et donc ne pas être affichées lors
 * d'un appel de la méthode toString() par exemple. <br>
 * Ne prend en compte que certains champs d'un calendrier. Cela inclue :
 * <ul>
 *     <li>Année</li>
 *     <li>Mois</li>
 *     <li>Jour du mois</li>
 *     <li>Heure du jour</li>
 *     <li>Minute</li>
 *     <li>Seconde</li>
 * </ul>
 * </p>
 * @author Camille Girodengo
 */
public class CalendarWithNulls {
    /**
     * Le calendrier contenant la date.
     */
    private Calendar fullDate = Calendar.getInstance();
    /**
     * Le array qui permet de déterminer si un champ du calendrier (reconnu par son identifiant dans Calendar)
     * a été set avec une valeur nulle (false) ou une valeur non nulle (true).
     */
    private boolean[] isDetermined = new boolean[Calendar.FIELD_COUNT];

    /**
     * Constructeur par défaut de la classe CalendarWithNulls.<br>
     * Toutes les champs pris en compte par la classe sont initialisés avec des valeurs nulles.
     */
    public CalendarWithNulls(){
        super();
        setAnnee(null);
        setMois(null);
        setJour(null);
        setHeure(null);
        setMinute(null);
        setSeconde(null);
    }

    /**
     * Constructeur complet de la classe CalendarWithNulls qui permet d'affecter des
     * valeurs à chaque champ de calendrier pris en compte par la classe.
     * @param annee La valeur associée au champ YEAR (Année) du Calendar
     * @param mois  La valeur associée au champ MONTH (Mois) du Calendar
     * @param jour  La valeur associée au champ DAY_OF_MONTH (Jour du mois) du Calendar
     * @param heure La valeur associée au champ HOUR_OF_DAY (Heure du jour) du Calendar
     * @param minute    La valeur associée au champ MINUTE (Minute) du Calendar
     * @param seconde   La valeur associée au champ SECOND (Seconde) du Calendar
     */
    public CalendarWithNulls(Integer annee, Integer mois, Integer jour, Integer heure, Integer minute, Integer seconde) {
        super();
        setAnnee(annee);
        setMois(mois);
        setJour(jour);
        setHeure(heure);
        setMinute(minute);
        setSeconde(seconde);
    }


    @Override
    public String toString(){
        String str = "CalendarWithNulls [\n\tannee=" + fullDate.get(Calendar.YEAR) +
                ",\n\tmois=" + fullDate.get(Calendar.MONTH) +
                ",\n\tjour=" + fullDate.get(Calendar.DAY_OF_MONTH) +
                ",\n\theure=" + fullDate.get(Calendar.HOUR_OF_DAY) +
                ",\n\tminute=" + fullDate.get(Calendar.MINUTE) +
                ",\n\tseconde=" + fullDate.get(Calendar.SECOND) +
                ",\n\tisDetermined= [";
        for (int indexField = 0; indexField < isDetermined.length; ++indexField){
            str = str + isDetermined[indexField];
            if (indexField == isDetermined.length - 1){
                str = str + "]";
            }
            else {
                str = str + ", ";
            }
        }
        str = str + " ]";
        return str;
    }

    /**
     * Revoie une string de la date sous le format initial AAAA/MM/JJ.<br>
     * Il existe des variations à ce format :
     * <ul>
     *     <li>Si seulement l'année est determinée -> AAAA/</li>
     *     <li>Si seulement le mois est determiné -> MM/</li>
     *     <li>Si seulement le jour est determiné -> /JJ</li>
     *     <li>Si seulement l'année et le jour sont déterminés -> AAAA//JJ</li>
     *     <li>Si seulement le mois et le jour sont determinés -> MM/JJ</li>
     * </ul>
     * @return String de date au format AAAA/MM/JJ
     */
    public String getDateString(){
        String date = "";

        if (getAnnee() != null){
            date = date + getAnnee() + "/";
        }
        /*else {
            date = date + "/";
        }*/
        if (getMois() == null){
            if (getJour() != null){
                date = date + "/" + getJour();
            }
        }
        else {
            if (getAnnee() == null && getJour() == null){
                date = date + "/";
            }
            date = date + (getMois()+1) + "/";
            if (getJour() != null){
                date = date + getJour();
            }
        }
        return date;
    }

    /**
     * Renvoie une string du temps sous le format initial "- h - min - sec".
     * Le format s'adapte aux valeurs non déterminées de manière à ce qu'il ne soit pas renvoyé une string avec trop d'espaces.
     * @return String de temps au format "- h - min - sec"
     */
    public String getTimeString() {
        String time = "";
        if (getHeure() != null) {
            time = time + getHeure() + " h";
            if (getMinute() != null || getSeconde() != null){
                time = time + " ";
            }
        }
        if (getMinute() != null){
            time = time + getMinute() + " min";
            if (getSeconde() != null){
                time = time + " ";
            }
        }
        if (getSeconde() != null){
            time = time + getSeconde() + " sec";
        }
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarWithNulls calendarWithNulls = (CalendarWithNulls) o;
        if (this.fullDate == null) {
            if (calendarWithNulls.fullDate != null) return false;
            return true;
        }
        else {
            // On utilise ici les getters car ils prennent aussi en compte les valeurs de isDetermined
            // Permettra double verif ou de se passer de la verif de isDetermined si les champs non utilisés dans isDetermined sont toujours en valeur pas défaut

            // Année
            if (this.getAnnee() == null){
                if (calendarWithNulls.getAnnee() != null) return false;
            }
            else {
                if (calendarWithNulls.getAnnee() == null) return false;
                if (this.getAnnee().intValue() != calendarWithNulls.getAnnee().intValue()) return false;
            }
            // Mois
            if (this.getMois() == null){
                if (calendarWithNulls.getMois() != null) return false;
            }
            else {
                if (calendarWithNulls.getMois() == null) return false;
                if (this.getMois().intValue() != calendarWithNulls.getMois().intValue()) return false;
            }
            // Jour
            if (this.getJour() == null){
                if (calendarWithNulls.getJour() != null) return false;
            }
            else {
                if (calendarWithNulls.getJour() == null) return false;
                if (this.getJour().intValue() != calendarWithNulls.getJour().intValue()) return false;
            }
            // Heure
            if (this.getHeure() == null){
                if (calendarWithNulls.getHeure() != null) return false;
            }
            else {
                if (calendarWithNulls.getHeure() == null) return false;
                if (this.getHeure().intValue() != calendarWithNulls.getHeure().intValue()) return false;
            }
            // Minute
            if (this.getMinute() == null){
                if (calendarWithNulls.getMinute() != null) return false;
            }
            else {
                if (calendarWithNulls.getMinute() == null) return false;
                if (this.getMinute().intValue() != calendarWithNulls.getMinute().intValue()) return false;
            }
            // Seconde
            if (this.getSeconde() == null){
                if (calendarWithNulls.getSeconde() != null) return false;
            }
            else {
                if (calendarWithNulls.getSeconde() == null) return false;
                if (this.getSeconde().intValue() != calendarWithNulls.getSeconde().intValue()) return false;
            }

        }

        return true;
    }

    /**
     * Une méthode void pour initialiser toutes les valeurs du boolean array isDetermined.
     * @param determined La valeur booléenne à laquelle vont être initialisées toutes les valeurs du array.
     */
    private void initializeIsDetermined(boolean determined){
        for (int indexField = 0; indexField < isDetermined.length; ++indexField){
            isDetermined[indexField] = determined;
        }
    }

    /*
    *   GETTERS
    */

    /**
     * Renvoie l'array de valeurs booléennes isDetermined qui indique si un champ du Calendar fullDate a une valeur associée déterminée (non nulle)
     * @return Renvoie le Boolean Array isDetermined.
     */
    public boolean[] getIsDetermined() {
        return isDetermined;
    }

    /**
     * Renvoie la valeur du champ YEAR (Année) du calendrier sauf si la valeur est "non déterminée" où la méthode renvoie null.
     * @return Si champ YEAR (Année) déterminé : Integer (valeur de l'année). Sinon renvoie null.
     */
    public Integer getAnnee(){
        if (isDetermined[Calendar.YEAR]) return fullDate.get(Calendar.YEAR);
        return null;
    }
    /**
     * Renvoie la valeur du champ MONTH (Mois) du calendrier sauf si la valeur est "non déterminée" où la méthode renvoie null.
     * @return Si champ MONTH (Mois) déterminé : Integer (valeur du mois). Sinon renvoie null.
     */
    public Integer getMois(){
        if (isDetermined[Calendar.MONTH]) return fullDate.get(Calendar.MONTH);
        return null;
    }
    /**
     * Renvoie la valeur du champ DAY_OF_MONTH (Jour du mois) du calendrier sauf si la valeur est "non déterminée" où la méthode renvoie null.
     * @return Si champ DAY_OF_MONTH (Jour du mois) déterminé : Integer (valeur du jour du mois). Sinon renvoie null.
     */
    public Integer getJour(){
        if (isDetermined[Calendar.DAY_OF_MONTH]) return fullDate.get(Calendar.DAY_OF_MONTH);
        return null;
    }
    /**
     * Renvoie la valeur du champ HOUR_OF_DAY (Heure du jour) du calendrier sauf si la valeur est "non déterminée" où la méthode renvoie null.
     * @return Si champ HOUR_OF_DAY (Heure du jour) déterminé : Integer (valeur de l'heure du jour). Sinon renvoie null.
     */
    public Integer getHeure(){
        if (isDetermined[Calendar.HOUR_OF_DAY]) return fullDate.get(Calendar.HOUR_OF_DAY);
        return null;
    }
    /**
     * Renvoie la valeur du champ MINUTE (Minute) du calendrier sauf si la valeur est "non déterminée" où la méthode renvoie null.
     * @return Si champ MINUTE (Minute) déterminé : Integer (valeur des minutes). Sinon renvoie null.
     */
    public Integer getMinute(){
        if (isDetermined[Calendar.MINUTE]) return fullDate.get(Calendar.MINUTE);
        return null;
    }
    /**
     * Renvoie la valeur du champ SECOND (Seconde) du calendrier sauf si la valeur est "non déterminée" où la méthode renvoie null.
     * @return Si champ SECOND (Seconde) déterminé : Integer (valeur des secondes). Sinon renvoie null.
     */
    public Integer getSeconde(){
        if (isDetermined[Calendar.SECOND]) return fullDate.get(Calendar.SECOND);
        return null;
    }

    /*
     *   SETTERS
     */

    public void setAnnee(Integer annee){
        if (annee == null){
            isDetermined[Calendar.YEAR] = false;
            fullDate.set(Calendar.YEAR, 1);
        }
        else {
            isDetermined[Calendar.YEAR] = true;
            fullDate.set(Calendar.YEAR, annee);
        }
    }
    public void setMois(Integer mois){
        if(mois == null){
            isDetermined[Calendar.MONTH] = false;
            fullDate.set(Calendar.MONTH, 1);
        }
        else {
            isDetermined[Calendar.MONTH] = true;
            fullDate.set(Calendar.MONTH, mois);
        }
    }
    public void setJour(Integer jour){
        if(jour == null){
            isDetermined[Calendar.DAY_OF_MONTH] = false;
            fullDate.set(Calendar.DAY_OF_MONTH, 1);
        }
        else {
            isDetermined[Calendar.DAY_OF_MONTH] = true;
            fullDate.set(Calendar.DAY_OF_MONTH, jour);
        }
    }
    public void setHeure(Integer heure){
        if(heure == null){
            isDetermined[Calendar.HOUR_OF_DAY] = false;
            fullDate.set(Calendar.HOUR_OF_DAY, 1);
        }
        else {
            isDetermined[Calendar.HOUR_OF_DAY] = true;
            fullDate.set(Calendar.HOUR_OF_DAY, heure);
        }
    }
    public void setMinute(Integer minute){
        if(minute == null){
            isDetermined[Calendar.MINUTE] = false;
            fullDate.set(Calendar.MINUTE, 1);
        }
        else {
            isDetermined[Calendar.MINUTE] = true;
            fullDate.set(Calendar.MINUTE, minute);
        }
    }
    public void setSeconde(Integer seconde){
        if(seconde == null){
            isDetermined[Calendar.SECOND] = false;
            fullDate.set(Calendar.SECOND, 1);
        }
        else {
            isDetermined[Calendar.SECOND] = true;
            fullDate.set(Calendar.SECOND, seconde);
        }
    }



}
