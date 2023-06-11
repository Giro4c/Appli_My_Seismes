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

    /**
     * Constructeur complet de la classe CalendarWithNulls qui permet d'affecter des
     * valeurs à chaque champ de calendrier pris en compte par la classe.
     * @param date Une String contennant une date au format AAAA/MM/JJ
     * @param time  Une String contennant une heure au format "-h - min - sec"
     */
    public CalendarWithNulls(String date, String time) {
        super();
        setDate(date);
        setTime(time);
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
     * Change la valeur de la date en fonction d'une string de format initial AAAA/MM/JJ.
     * Ghange ainsi les valeurs des champs YEAR (Année), MONTH (Mois) et DAY_OF_MONTH (Jour du mois)
     * @param date String de format initial AAAA/MM/JJ
     */
    public void setDate(String date){
        String[] split = date.split("/");
        if (split.length == 1){
            if (split[0].equals("")){
                setAnnee(null);
            }
            else{
                setAnnee(Integer.valueOf(split[0]));
            }
            setMois(null);
            setJour(null);
        }
        else if (split.length == 2){
            if (date.charAt(0) == '/'){
                setAnnee(null);
                if (date.charAt(date.length() - 1) == '/'){
                    setMois(Integer.valueOf(split[1]) - 1);
                    setJour(null);
                }
                else{
                    setMois(null);
                    setJour(Integer.valueOf(split[1]));
                }
            }
            else if (date.charAt(date.length() - 1) == '/'){
                setAnnee(Integer.valueOf(split[0]));
                setMois(Integer.valueOf(split[1]) - 1);
                setJour(null);
            }
            else{
                setAnnee(null);
                setMois(Integer.valueOf(split[0]) - 1);
                setJour(Integer.valueOf(split[1]));
            }
        }
        else if (split.length == 3){
            setAnnee(Integer.valueOf(split[0]));
            if (split[1].equals("")){
                setMois(null);
            }
            else{
                setMois(Integer.valueOf(split[1]) - 1);
            }
            setJour(Integer.valueOf(split[2]));
        }
        else {
            System.err.println("Error: Incorrect Date String format.");
        }
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

    /**
     * Change la valeur de la date en fonction d'une string de format initial "- h - min - sec".
     * Ghange ainsi les valeurs des champs HOUR_OF_DAY (Heure du jour), MINUTE (Minute) et SECOND (Seconde)
     * @param time String de format initial "- h - min - sec"
     */
    public void setTime(String time){
        String[] split = time.split(" ");
        boolean hourSet = false;
        boolean minuteSet = false;
        boolean secondSet = false;
        if (split.length < 7) {
            if (split.length > 1) {
                try {
                    for (int indexValue = 0; indexValue < split.length - 1; indexValue = indexValue + 2) {
                        if (split[indexValue + 1].equals("h")) {
                            setHeure(Integer.valueOf(split[indexValue]));
                            hourSet = true;
                        } else if (split[indexValue + 1].equals("min")) {
                            setMinute(Integer.valueOf(split[indexValue]));
                            minuteSet = true;
                        } else if (split[indexValue + 1].equals("sec")) {
                            setSeconde(Integer.valueOf(split[indexValue]));
                            secondSet = true;
                        }
                    }
                } catch (Exception e){
                    System.out.println("Erreur sur la String time : " + time);
                }
            }
            if (!hourSet) {
                setHeure(null);
            }
            if (!minuteSet) {
                setMinute(null);
            }
            if (!secondSet) {
                setSeconde(null);
            }
        }
        else {
            System.err.println("Error: Incorrect Time String format.");
        }
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
     * Permet de déterminer si la date de ce CalendarWithNulls est inférieure ou égale à celle d'un autre CalendarWithNulls calendar.
     * Dans le cas où un champ de date est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param calendar Le CalendarWithNulls auquel on compare la valeur de la date.
     * @return Renvoie true si la date est inférieure ou égale. Renvoie false si elle est strictement supérieure.
     */
    public boolean isDateInferiorOrEqualsTo(CalendarWithNulls calendar){
        // Année
        if (this.getAnnee() != null && calendar.getAnnee() != null){
            if (this.getAnnee() > calendar.getAnnee()) return false;
            if (this.getAnnee() < calendar.getAnnee()) return true;
        }
        // Mois
        if (this.getMois() != null && calendar.getMois() != null){
            if (this.getMois() > calendar.getMois()) return false;
            if (this.getMois() < calendar.getMois()) return true;
        }
        // Jour
        if (this.getJour() != null && calendar.getJour() != null){
            if (this.getJour() > calendar.getJour()) return false;
            if (this.getJour() < calendar.getJour()) return true;
        }

        return true;
    }

    /**
     * Permet de déterminer si la date de ce CalendarWithNulls est inférieure ou égale à celle contenue dans une String format AAAA/MM/JJ.
     * Dans le cas où un champ de date est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param dateRef Une date sous forme de String au format AAAA/MM/JJ à laquelle est comparée la date.
     * @return Renvoie true si la date est inférieure ou égale. Renvoie false si elle est strictement supérieure.
     */
    public boolean isDateInferiorOrEqualsTo(String dateRef){
        CalendarWithNulls calendarRef = new CalendarWithNulls(dateRef, "");
        return isDateInferiorOrEqualsTo(calendarRef);
    }

    /**
     * Permet de déterminer si la date de ce CalendarWithNulls est supérieure ou égale à celle d'un autre CalendarWithNulls calendar.
     * Dans le cas où un champ de date est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param calendar Le CalendarWithNulls auquel on compare la valeur de la date.
     * @return Renvoie true si la date est supérieure ou égale. Renvoie false si elle est strictement inférieure.
     */
    public boolean isDateSuperiorOrEqualsTo(CalendarWithNulls calendar){
        // Année
        if (this.getAnnee() != null && calendar.getAnnee() != null){
            if (this.getAnnee() > calendar.getAnnee()) return true;
            if (this.getAnnee() < calendar.getAnnee()) return false;
        }
        // Mois
        if (this.getMois() != null && calendar.getMois() != null){
            if (this.getMois() > calendar.getMois()) return true;
            if (this.getMois() < calendar.getMois()) return false;
        }
        // Jour
        if (this.getJour() != null && calendar.getJour() != null){
            if (this.getJour() > calendar.getJour()) return true;
            if (this.getJour() < calendar.getJour()) return false;
        }

        return true;
    }

    /**
     * Permet de déterminer si la date de ce CalendarWithNulls est supérieure ou égale à celle contenue dans une String format AAAA/MM/JJ.
     * Dans le cas où un champ de date est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param dateRef Une date sous forme de String au format AAAA/MM/JJ à laquelle est comparée la date.
     * @return Renvoie true si la date est supérieure ou égale. Renvoie false si elle est strictement inférieure.
     */
    public boolean isDateSuperiorOrEqualsTo(String dateRef){
        CalendarWithNulls calendarRef = new CalendarWithNulls(dateRef, "");
        return isDateSuperiorOrEqualsTo(calendarRef);
    }

    /**
     * Permet de déterminer si l'heure de ce CalendarWithNulls est inférieure ou égale à celle d'un autre CalendarWithNulls calendar.
     * Dans le cas où un champ d'heure est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param calendar Le CalendarWithNulls auquel on compare la valeur de l'heure.
     * @return Renvoie true si l'heure est inférieure ou égale. Renvoie false si elle est strictement supérieure.
     */
    public boolean isTimeInferiorOrEqualsTo(CalendarWithNulls calendar){
        // Heure
        if (this.getHeure() != null && calendar.getHeure() != null){
            if (this.getHeure() > calendar.getHeure()) return false;
            if (this.getHeure() < calendar.getHeure()) return true;
        }
        // Minute
        if (this.getMinute() != null && calendar.getMinute() != null){
            if (this.getMinute() > calendar.getMinute()) return false;
            if (this.getMinute() < calendar.getMinute()) return true;
        }
        // Seconde
        if (this.getSeconde() != null && calendar.getSeconde() != null){
            if (this.getSeconde() > calendar.getSeconde()) return false;
            if (this.getSeconde() < calendar.getSeconde()) return true;
        }

        return true;
    }

    /**
     * Permet de déterminer si l'heure de ce CalendarWithNulls est inférieure ou égale à celle contenue dans une String format "-h - min - sec".
     * Dans le cas où un champ d'heure est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param timeRef Une heure sous forme de String au format "-h - min - sec" à laquelle est comparée l'heure.
     * @return Renvoie true si l'heure est inférieure ou égale. Renvoie false si elle est ou égale supérieure.
     */
    public boolean isTimeInferiorOrEqualsTo(String timeRef){
        CalendarWithNulls calendarRef = new CalendarWithNulls("", timeRef);
        return isTimeInferiorOrEqualsTo(calendarRef);
    }

    /**
     * Permet de déterminer si l'heure de ce CalendarWithNulls est supérieure ou égale à celle d'un autre CalendarWithNulls calendar.
     * Dans le cas où un champ d'heure est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param calendar Le CalendarWithNulls auquel on compare la valeur de l'heure.
     * @return Renvoie true si l'heure est supérieure ou égale. Renvoie false si elle est strictement inférieure.
     */
    public boolean isTimeSuperiorOrEqualsTo(CalendarWithNulls calendar){
        // Heure
        if (this.getHeure() != null && calendar.getHeure() != null){
            if (this.getHeure() > calendar.getHeure()) return true;
            if (this.getHeure() < calendar.getHeure()) return false;
        }
        // Minute
        if (this.getMinute() != null && calendar.getMinute() != null){
            if (this.getMinute() > calendar.getMinute()) return true;
            if (this.getMinute() < calendar.getMinute()) return false;
        }
        // Seconde
        if (this.getSeconde() != null && calendar.getSeconde() != null){
            if (this.getSeconde() > calendar.getSeconde()) return true;
            if (this.getSeconde() < calendar.getSeconde()) return false;
        }

        return true;
    }

    /**
     * Permet de déterminer si l'heure de ce CalendarWithNulls est supérieure ou égale à celle contenue dans une String format "-h - min - sec".
     * Dans le cas où un champ d'heure est null, ce champ est considéré comme valide quoi qu'il arrive.
     * @param timeRef Une heure sous forme de String au format "-h - min - sec" à laquelle est comparée l'heure.
     * @return Renvoie true si l'heure est supérieure ou égale. Renvoie false si elle est inférieure.
     */
    public boolean isTimeSuperiorOrEqualsTo(String timeRef){
        CalendarWithNulls calendarRef = new CalendarWithNulls("", timeRef);
        return isTimeSuperiorOrEqualsTo(calendarRef);
    }

    /**
     * Une méthode void pour initialiser toutes les valeurs du boolean array isDetermined.
     * @param determined La valeur booléenne à laquelle vont être initialisées toutes les valeurs du array.
     * @deprecated Valeurs déterminées automatiquement dans le contructeur via les setters particuliers.
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

    /**
     * Modifie la valeur de l'attribut Année du calendrier et du champs lié à l'Année dans isDetermined.
     * <ul>
     *     <li>Si la valeur donnée est null, alors la valeur de l'année est set à la valeur par défaut 1 et le champ de l'année dans
     * isDetermined est changé à false pour signifier que la valeur est non déterminée.</li>
     * <li>Dans le cas contraire où la valeur donnée n'est pas null, le champ Année du calendrier prend la valeur donnée et le
     * champ associé dans isDetermined est changé à true pour signifier que la valeur est déterminée.</li>
     * </ul>
     * @param annee L'Integer représentant la nouvelle année du calendrier. Ce paramètre peut valoir null.
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

    /**
     * Modifie la valeur de l'attribut Mois du calendrier et du champs lié au Mois dans isDetermined.
     * <ul>
     *     <li>Si la valeur donnée est null, alors la valeur du mois est set à la valeur par défaut 1 et le champ du mois dans
     * isDetermined est changé à false pour signifier que la valeur est non déterminée.</li>
     * <li>Dans le cas contraire où la valeur donnée n'est pas null, le champ Mois du calendrier prend la valeur donnée et le
     * champ associé dans isDetermined est changé à true pour signifier que la valeur est déterminée.</li>
     * </ul>
     * @param mois L'Integer représentant le nouveaux mois du calendrier. Ce paramètre peut valoir null.
     */
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

    /**
     * Modifie la valeur de l'attribut Jour du mois du calendrier et du champs lié au Jour du mois dans isDetermined.
     * <ul>
     *     <li>Si la valeur donnée est null, alors la valeur du jour (du mois) est set à la valeur par défaut 1 et le champ du jour (du mois) dans
     * isDetermined est changé à false pour signifier que la valeur est non déterminée.</li>
     * <li>Dans le cas contraire où la valeur donnée n'est pas null, le champ Jour du mois du calendrier prend la valeur donnée et le
     * champ associé dans isDetermined est changé à true pour signifier que la valeur est déterminée.</li>
     * </ul>
     * @param jour L'Integer représentant le nouveau jour du mois du calendrier. Ce paramètre peut valoir null.
     */
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

    /**
     * Modifie la valeur de l'attribut Heure du jour du calendrier et du champs lié à l'Heure du jour dans isDetermined.
     * <ul>
     *     <li>Si la valeur donnée est null, alors la valeur de l'heure est set à la valeur par défaut 1 et le champ de l'heure dans
     * isDetermined est changé à false pour signifier que la valeur est non déterminée.</li>
     * <li>Dans le cas contraire où la valeur donnée n'est pas null, le champ Heure du jour du calendrier prend la valeur donnée et le
     * champ associé dans isDetermined est changé à true pour signifier que la valeur est déterminée.</li>
     * </ul>
     * @param heure L'Integer représentant la nouvelle heure du calendrier. Ce paramètre peut valoir null.
     */
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

    /**
     * Modifie la valeur de l'attribut Minute du calendrier et du champs lié à la Minute dans isDetermined.
     * <ul>
     *     <li>Si la valeur donnée est null, alors la valeur de la minute est set à la valeur par défaut 1 et le champ de la minute dans
     * isDetermined est changé à false pour signifier que la valeur est non déterminée.</li>
     *     <li>Dans le cas contraire où la valeur donnée n'est pas null, le champ Minute du calendrier prend la valeur donnée et le
     * champ associé dans isDetermined est changé à true pour signifier que la valeur est déterminée.</li>
     * </ul>
     * @param minute L'Integer représentant la nouvelle minute du calendrier. Ce paramètre peut valoir null.
     */
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

    /**
     * Modifie la valeur de l'attribut Seconde du calendrier et du champs lié aux Secondes dans isDetermined.
     * <ul>
     *      <li>Si la valeur donnée est null, alors la valeur des secondes est set à la valeur par défaut 1 et le champ des secondes dans
     * isDetermined est changé à false pour signifier que la valeur est non déterminée.</li>
     * <li>Dans le cas contraire où la valeur donnée n'est pas null, le champ Seconde du calendrier prend la valeur donnée et le
     * champ associé dans isDetermined est changé à true pour signifier que la valeur est déterminée.</li>
     * </ul>
     * @param seconde L'Integer représentant la nouvelle seconde du calendrier. Ce paramètre peut valoir null.
     */
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
