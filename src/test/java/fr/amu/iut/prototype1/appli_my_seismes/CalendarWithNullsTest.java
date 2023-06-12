package fr.amu.iut.prototype1.appli_my_seismes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CalendarWithNullsTest {

    @Test
    void testToString() {

        CalendarWithNulls calendar = new CalendarWithNulls();
        StringBuilder test1 = new StringBuilder("CalendarWithNulls [\n\tannee=1,\n\tmois=1,\n\tjour=1,\n\theure=1,\n\tminute=1,\n\tseconde=1,\n\tisDetermined= [");
        for (int count = 0; count < Calendar.FIELD_COUNT - 1; ++count){
            test1.append("false, ");
        }
        test1.append("false] ]");
        assertEquals(test1.toString(), calendar.toString());

        // Test pour valeurs stockées identiques sauf pour isDetermined
        calendar.setMois(1);
        StringBuilder test2 = new StringBuilder("CalendarWithNulls [\n\tannee=1,\n\tmois=1,\n\tjour=1,\n\theure=1,\n\tminute=1,\n\tseconde=1,\n\tisDetermined= [");
        for (int count = 0; count < Calendar.FIELD_COUNT - 1; ++count){
            if (count == Calendar.MONTH){
                test2.append("true, ");
            }
            else{
                test2.append("false, ");
            }
        }
        test2.append("false] ]");
        assertEquals(test2.toString(), calendar.toString());
        assertNotEquals(test1.toString(), calendar.toString());

        // Test pour valeurs stockées différentes et diff isDetermined
        calendar.setMois(5);
        StringBuilder test3 = new StringBuilder("CalendarWithNulls [\n\tannee=1,\n\tmois=5,\n\tjour=1,\n\theure=1,\n\tminute=1,\n\tseconde=1,\n\tisDetermined= [");
        for (int count = 0; count < Calendar.FIELD_COUNT - 1; ++count){
            if (count == Calendar.MONTH){
                test3.append("true, ");
            }
            else{
                test3.append("false, ");
            }
        }
        test3.append("false] ]");

        assertEquals(test3.toString(), calendar.toString());
        assertNotEquals(test2.toString(), calendar.toString());

    }

    @Test
    void getDateString() {
        CalendarWithNulls calendar = new CalendarWithNulls(2023, 6, 5, 13, 40, 24);
        // Test Format : AAAA/MM/JJ
        assertEquals("2023/7/5", calendar.getDateString());
        // Test Format : AAAA/MM/
        calendar.setJour(null);
        assertEquals("2023/7/", calendar.getDateString());
        // Test Format : AAAA/
        calendar.setMois(null);
        assertEquals("2023/", calendar.getDateString());
        // Test Format : AAAA//JJ
        calendar.setJour(5);
        assertEquals("2023//5", calendar.getDateString());
        // Test Format : /JJ
        calendar.setAnnee(null);
        assertEquals("/5", calendar.getDateString());
        // Test Format : MM/JJ
        calendar.setMois(6);
        assertEquals("7/5", calendar.getDateString());
        // Test Format : /MM/
        calendar.setJour(null);
        assertEquals("/7/", calendar.getDateString());

    }

    @Test
    void getTimeString() {
        CalendarWithNulls calendar = new CalendarWithNulls(2023, 6, 5, 13, 40, 24);
        // Test Format : "- h - min - sec"
        assertEquals("13 h 40 min 24 sec", calendar.getTimeString());
        // Test Format : "- h - min"
        calendar.setSeconde(null);
        assertEquals("13 h 40 min", calendar.getTimeString());
        // Test Format : "- h"
        calendar.setMinute(null);
        assertEquals("13 h", calendar.getTimeString());
        // Test Format : "- h - sec"
        calendar.setSeconde(24);
        assertEquals("13 h 24 sec", calendar.getTimeString());
        // Test Format : "- sec"
        calendar.setHeure(null);
        assertEquals("24 sec", calendar.getTimeString());
        // Test Format : "- min - sec"
        calendar.setMinute(40);
        assertEquals("40 min 24 sec", calendar.getTimeString());
        // Test Format : "- min"
        calendar.setSeconde(null);
        assertEquals("40 min", calendar.getTimeString());

    }

    @Test
    void testEquals() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(2022, 6, 5, 15, null, 2);
        CalendarWithNulls calendar2 = new CalendarWithNulls(2022, 6, 5, 15, null, 2);
        CalendarWithNulls calendar3 = new CalendarWithNulls(2023, 6, 5, 15, 1, 2);
        CalendarWithNulls calendar4 = new CalendarWithNulls(2000, 6, 5, 15, 1, 2);
        CalendarWithNulls calendar5 = new CalendarWithNulls(2023, 2, 5, 15, 1, 2);
        CalendarWithNulls calendar6 = new CalendarWithNulls(2023, 6, 26, 15, 1, 2);
        CalendarWithNulls calendar7 = new CalendarWithNulls(2023, 6, 5, 12, 1, 2);
        CalendarWithNulls calendar8 = new CalendarWithNulls(2023, 6, 5, 15, 1, 40);

        // Test equals : Identiques
        assertEquals(calendar1, calendar2);
        // Test equals : Années différentes
        assertNotEquals(calendar3, calendar4);
        // Test equals : Mois différents
        assertNotEquals(calendar3, calendar5);
        // Test equals : Jours différents
        assertNotEquals(calendar3, calendar6);
        // Test equals : Heures différentes
        assertNotEquals(calendar3, calendar7);
        // Test equals : Minutes différentes + isDetermined différents
        assertNotEquals(calendar3, calendar1);
        // Test equals : Secondes différentes
        assertNotEquals(calendar3, calendar8);

    }

    @Test
    void getAnnee() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(1,1,1,1,1,1);
        CalendarWithNulls calendar2 = new CalendarWithNulls(null,1,1,1,1,1);
        // Test get avec valeur déterminée
        assertEquals(1, calendar1.getAnnee());
        // Test get avec valeur non déterminée
        assertNull(calendar2.getAnnee());

    }

    @Test
    void getMois() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(1,1,1,1,1,1);
        CalendarWithNulls calendar2 = new CalendarWithNulls(1,null,1,1,1,1);
        // Test get avec valeur déterminée
        assertEquals(1, calendar1.getMois());
        // Test get avec valeur non déterminée
        assertNull(calendar2.getMois());
    }

    @Test
    void getJour() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(1,1,1,1,1,1);
        CalendarWithNulls calendar2 = new CalendarWithNulls(1,1,null,1,1,1);
        // Test get avec valeur déterminée
        assertEquals(1, calendar1.getJour());
        // Test get avec valeur non déterminée
        assertNull(calendar2.getJour());
    }

    @Test
    void getHeure() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(1,1,1,1,1,1);
        CalendarWithNulls calendar2 = new CalendarWithNulls(1,1,1,null,1,1);
        // Test get avec valeur déterminée
        assertEquals(1, calendar1.getHeure());
        // Test get avec valeur non déterminée
        assertNull(calendar2.getHeure());
    }

    @Test
    void getMinute() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(1,1,1,1,1,1);
        CalendarWithNulls calendar2 = new CalendarWithNulls(1,1,1,1,null,1);
        // Test get avec valeur déterminée
        assertEquals(1, calendar1.getMinute());
        // Test get avec valeur non déterminée
        assertNull(calendar2.getMinute());
    }

    @Test
    void getSeconde() {
        CalendarWithNulls calendar1 = new CalendarWithNulls(1,1,1,1,1,1);
        CalendarWithNulls calendar2 = new CalendarWithNulls(1,1,1,1,1,null);
        // Test get avec valeur déterminée
        assertEquals(1, calendar1.getSeconde());
        // Test get avec valeur non déterminée
        assertNull(calendar2.getSeconde());
    }

    @Test
    void setAnnee() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test set avec valeur non nulle
        calendar.setAnnee(2020);
        assertEquals(2020, calendar.getAnnee());
        assertTrue(calendar.getIsDetermined()[Calendar.YEAR]);
        // Année minimal du calendrier : 1
        calendar.setAnnee(0);
        assertEquals(1, calendar.getAnnee());
        assertTrue(calendar.getIsDetermined()[Calendar.YEAR]);
        // Test set avec valeur nulle
        calendar.setAnnee(null);
        assertNull(calendar.getAnnee());
        assertFalse(calendar.getIsDetermined()[Calendar.YEAR]);
    }

    @Test
    void setMois() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test set avec valeur non nulle
        calendar.setMois(11);
        assertEquals(11, calendar.getMois());
        assertTrue(calendar.getIsDetermined()[Calendar.MONTH]);
        // Test set avec valeur nulle
        calendar.setMois(null);
        assertNull(calendar.getMois());
        assertFalse(calendar.getIsDetermined()[Calendar.MONTH]);
    }

    @Test
    void setJour() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test set avec valeur non nulle
        calendar.setJour(3);
        assertEquals(3, calendar.getJour());
        assertTrue(calendar.getIsDetermined()[Calendar.DAY_OF_MONTH]);
        // Test set avec valeur nulle
        calendar.setJour(null);
        assertNull(calendar.getJour());
        assertFalse(calendar.getIsDetermined()[Calendar.DAY_OF_MONTH]);
    }

    @Test
    void setHeure() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test set avec valeur non nulle
        calendar.setHeure(14);
        assertEquals(14, calendar.getHeure());
        assertTrue(calendar.getIsDetermined()[Calendar.HOUR_OF_DAY]);
        // Test set avec valeur nulle
        calendar.setHeure(null);
        assertNull(calendar.getHeure());
        assertFalse(calendar.getIsDetermined()[Calendar.HOUR_OF_DAY]);
    }

    @Test
    void setMinute() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test set avec valeur non nulle
        calendar.setMinute(59);
        assertEquals(59, calendar.getMinute());
        assertTrue(calendar.getIsDetermined()[Calendar.MINUTE]);
        // Test set avec valeur nulle
        calendar.setMinute(null);
        assertNull(calendar.getMinute());
        assertFalse(calendar.getIsDetermined()[Calendar.MINUTE]);
    }

    @Test
    void setSeconde() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test set avec valeur non nulle
        calendar.setSeconde(47);
        assertEquals(47, calendar.getSeconde());
        assertTrue(calendar.getIsDetermined()[Calendar.SECOND]);
        // Test set avec valeur nulle
        calendar.setSeconde(null);
        assertNull(calendar.getSeconde());
        assertFalse(calendar.getIsDetermined()[Calendar.SECOND]);
    }

    @Test
    void setDate() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test Format : ""
        calendar.setDate("");
        assertTrue((calendar.getAnnee() == null && calendar.getMois() == null && calendar.getJour() == null));

        // Test Format : AAAA/MM/JJ
        calendar.setDate("2023/7/5");
        assertTrue((calendar.getAnnee() == 2023 && calendar.getMois() == 6 && calendar.getJour() == 5));

        // Test Format : AAAA/MM/
        calendar.setDate("2023/7/");
        assertTrue((calendar.getAnnee() == 2023 && calendar.getMois() == 6 && calendar.getJour() == null));

        // Test Format : AAAA/
        calendar.setDate("2023/");
        assertTrue((calendar.getAnnee() == 2023 && calendar.getMois() == null && calendar.getJour() == null));

        // Test Format : AAAA//JJ
        calendar.setDate("2023//5");
        assertTrue((calendar.getAnnee() == 2023 && calendar.getMois() == null && calendar.getJour() == 5));

        // Test Format : /JJ
        calendar.setDate("/5");
        assertTrue((calendar.getAnnee() == null && calendar.getMois() == null && calendar.getJour() == 5));

        // Test Format : MM/JJ
        calendar.setDate("7/5");
        assertTrue((calendar.getAnnee() == null && calendar.getMois() == 6 && calendar.getJour() == 5));

        // Test Format : /MM/
        calendar.setDate("/7/");
        assertTrue((calendar.getAnnee() == null && calendar.getMois() == 6 && calendar.getJour() == null));

    }

    @Test
    void setTime() {
        CalendarWithNulls calendar = new CalendarWithNulls(1,1,1,1,1,1);
        // Test Format : ""
        calendar.setTime("");
        assertTrue((calendar.getHeure() == null && calendar.getMinute() == null && calendar.getSeconde() == null));

        // Test Format : "- h - min - sec"
        calendar.setTime("13 h 40 min 24 sec");
        assertTrue((calendar.getHeure() == 13 && calendar.getMinute() == 40 && calendar.getSeconde() == 24));

        // Test Format : "- h - min"
        calendar.setTime("13 h 40 min");
        assertTrue((calendar.getHeure() == 13 && calendar.getMinute() == 40 && calendar.getSeconde() == null));

        // Test Format : "- h"
        calendar.setTime("13 h");
        assertTrue((calendar.getHeure() == 13 && calendar.getMinute() == null && calendar.getSeconde() == null));

        // Test Format : "- h - sec"
        calendar.setTime("13 h 24 sec");
        assertTrue((calendar.getHeure() == 13 && calendar.getMinute() == null && calendar.getSeconde() == 24));

        // Test Format : "- sec"
        calendar.setTime("24 sec");
        assertTrue((calendar.getHeure() == null && calendar.getMinute() == null && calendar.getSeconde() == 24));

        // Test Format : "- min - sec"
        calendar.setTime("40 min 24 sec");
        assertTrue((calendar.getHeure() == null && calendar.getMinute() == 40 && calendar.getSeconde() == 24));

        // Test Format : "- min"
        calendar.setTime("40 min");
        assertTrue((calendar.getHeure() == null && calendar.getMinute() == 40 && calendar.getSeconde() == null));

    }


    @Test
    void isDateInferiorOrEqualsTo() {
        CalendarWithNulls calendar1 = new CalendarWithNulls("2/2/2","");
        CalendarWithNulls calendar2 = new CalendarWithNulls("2/2/2","");
        // Tous les cas censés renvoyer true
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("1/2/2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/1/2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/2/1"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/1"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("1/1"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("1/2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2//2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2//1"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("1//2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("/2"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("/1"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("/1/"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("/2/"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("1/"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/"); //
        assertTrue(calendar1.isDateInferiorOrEqualsTo(calendar2));

        // Tous les cas censés renvoyer false

        calendar1.setDate("");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/2/3");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/3/2");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("3/2/2");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2/3");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("3/2");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("2//3");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("3//2");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("3/");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));
        calendar1.setDate("/3/");
        assertFalse(calendar1.isDateInferiorOrEqualsTo(calendar2));

    }

    @Test
    void isDateSuperiorOrEqualsTo() {
        CalendarWithNulls calendar1 = new CalendarWithNulls("2/2/2","");
        CalendarWithNulls calendar2 = new CalendarWithNulls("2/2/2","");
        // Tous les cas censés renvoyer true
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/2"); //
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2//2"); //
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("/2"); //
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("/2/"); //
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/"); //
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/2/3");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/3/2");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("3/2/2");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/3");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("3/2");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2//3");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("3//2");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("3/");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("/3/");
        assertTrue(calendar1.isDateSuperiorOrEqualsTo(calendar2));

        // Tous les cas censés renvoyer false

        calendar1.setDate("");
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("1/2/2"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/1/2"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/2/1"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2/1"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("1/1"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("1/2"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("2//1"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("1//2"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("/1"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("/1/"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));
        calendar1.setDate("1/"); //
        assertFalse(calendar1.isDateSuperiorOrEqualsTo(calendar2));

    }

    @Test
    void isTimeInferiorOrEqualsTo() {
        CalendarWithNulls calendar1 = new CalendarWithNulls("","2 h 2 min 2 sec");
        CalendarWithNulls calendar2 = new CalendarWithNulls("","2 h 2 min 2 sec");
        // Tous les cas censés renvoyer true
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 h 2 min 2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 1 min 2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 2 min 1 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min 1 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 min 1 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 min 2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min 2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 1 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 h 2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 sec"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 min"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("1 h"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h"); //
        assertTrue(calendar1.isTimeInferiorOrEqualsTo(calendar2));

        // Tous les cas censés renvoyer false

        calendar1.setTime("");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 2 min 3 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 3 min 2 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("3 h 2 min 2 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min 3 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("3 min 2 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 3 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("3 h 2 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("3 h");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("3 sec");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));
        calendar1.setTime("3 min");
        assertFalse(calendar1.isTimeInferiorOrEqualsTo(calendar2));

    }

    @Test
    void isTimeSuperiorOrEqualsTo() {
        CalendarWithNulls calendar1 = new CalendarWithNulls("","2 h 2 min 2 sec");
        CalendarWithNulls calendar2 = new CalendarWithNulls("","2 h 2 min 2 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min 2 sec"); //
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 2 sec"); //
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 sec"); //
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min"); //
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h"); //
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 2 min 3 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 3 min 2 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("3 h 2 min 2 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min 3 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("3 min 2 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 3 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("3 h 2 sec");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("3 h");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("3 min");
        assertTrue(calendar1.isTimeSuperiorOrEqualsTo(calendar2));

        // Tous les cas censés renvoyer false

        calendar1.setTime("");
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 h 2 min 2 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 1 min 2 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 2 min 1 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 min 1 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 min 1 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 min 2 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("2 h 1 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 h 2 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 sec"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 min"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
        calendar1.setTime("1 h"); //
        assertFalse(calendar1.isTimeSuperiorOrEqualsTo(calendar2));
    }
}