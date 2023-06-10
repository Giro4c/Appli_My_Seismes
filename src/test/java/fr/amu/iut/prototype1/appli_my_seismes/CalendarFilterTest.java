package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalendarFilterTest {

    @Test
    void testBindingFilter(){
        StringProperty minAnnee = new SimpleStringProperty("1950");
        StringProperty maxAnnee = new SimpleStringProperty("2023");
        StringProperty minMois = new SimpleStringProperty("5");
        StringProperty maxMois = new SimpleStringProperty("9");
        StringProperty minJour = new SimpleStringProperty("2");
        StringProperty maxJour = new SimpleStringProperty("19");
        IntegerProperty minHeure = new SimpleIntegerProperty(8);
        IntegerProperty maxHeure = new SimpleIntegerProperty(22);
        IntegerProperty minMinute = new SimpleIntegerProperty(10);
        IntegerProperty maxMinute = new SimpleIntegerProperty(30);
        IntegerProperty minSeconde = new SimpleIntegerProperty(45);
        IntegerProperty maxSeconde = new SimpleIntegerProperty(50);

        CalendarFilter calendarFilter = new CalendarFilter(minAnnee, maxAnnee, minMois, maxMois, minJour, maxJour,
                minHeure, maxHeure, minMinute, maxMinute, minSeconde, maxSeconde);

        assertEquals("1950", calendarFilter.getMinAnnee());
        assertEquals(1950, calendarFilter.getMinCalendar().getAnnee());
        assertEquals("2023", calendarFilter.getMaxAnnee());
        assertEquals(2023, calendarFilter.getMaxCalendar().getAnnee());
        assertEquals("5", calendarFilter.getMinMois());
        assertEquals(4, calendarFilter.getMinCalendar().getMois());
        assertEquals("9", calendarFilter.getMaxMois());
        assertEquals(8, calendarFilter.getMaxCalendar().getMois());
        assertEquals("2", calendarFilter.getMinJour());
        assertEquals(2, calendarFilter.getMinCalendar().getJour());
        assertEquals("19", calendarFilter.getMaxJour());
        assertEquals(19, calendarFilter.getMaxCalendar().getJour());
        assertEquals(8, calendarFilter.getMinHeure());
        assertEquals(8, calendarFilter.getMinCalendar().getHeure());
        assertEquals(22, calendarFilter.getMaxHeure());
        assertEquals(22, calendarFilter.getMaxCalendar().getHeure());
        assertEquals(10, calendarFilter.getMinMinute());
        assertEquals(10, calendarFilter.getMinCalendar().getMinute());
        assertEquals(30, calendarFilter.getMaxMinute());
        assertEquals(30, calendarFilter.getMaxCalendar().getMinute());
        assertEquals(45, calendarFilter.getMinSeconde());
        assertEquals(45, calendarFilter.getMinCalendar().getSeconde());
        assertEquals(50, calendarFilter.getMaxSeconde());
        assertEquals(50, calendarFilter.getMaxCalendar().getSeconde());


    }

    @Test
    void matchDateFilter() {
        StringProperty minAnnee = new SimpleStringProperty("1950");
        StringProperty maxAnnee = new SimpleStringProperty("2023");
        StringProperty minMois = new SimpleStringProperty("5");
        StringProperty maxMois = new SimpleStringProperty("9");
        StringProperty minJour = new SimpleStringProperty("2");
        StringProperty maxJour = new SimpleStringProperty("19");
        IntegerProperty minHeure = new SimpleIntegerProperty(8);
        IntegerProperty maxHeure = new SimpleIntegerProperty(22);
        IntegerProperty minMinute = new SimpleIntegerProperty(10);
        IntegerProperty maxMinute = new SimpleIntegerProperty(30);
        IntegerProperty minSeconde = new SimpleIntegerProperty(45);
        IntegerProperty maxSeconde = new SimpleIntegerProperty(50);

        CalendarFilter calendarFilter = new CalendarFilter(minAnnee, maxAnnee, minMois, maxMois, minJour, maxJour,
                minHeure, maxHeure, minMinute, maxMinute, minSeconde, maxSeconde);

        assertTrue(calendarFilter.matchDateFilter("1950/5/2"));
        assertTrue(calendarFilter.matchDateFilter("2023/9/19"));
        assertTrue(calendarFilter.matchDateFilter("1969/4/1"));
        assertFalse(calendarFilter.matchDateFilter("1950/4/1"));
        assertFalse(calendarFilter.matchDateFilter("2023/9/20"));
    }

    @Test
    void matchTimeFilter() {
        StringProperty minAnnee = new SimpleStringProperty("1950");
        StringProperty maxAnnee = new SimpleStringProperty("2023");
        StringProperty minMois = new SimpleStringProperty("5");
        StringProperty maxMois = new SimpleStringProperty("9");
        StringProperty minJour = new SimpleStringProperty("2");
        StringProperty maxJour = new SimpleStringProperty("19");
        IntegerProperty minHeure = new SimpleIntegerProperty(8);
        IntegerProperty maxHeure = new SimpleIntegerProperty(22);
        IntegerProperty minMinute = new SimpleIntegerProperty(10);
        IntegerProperty maxMinute = new SimpleIntegerProperty(30);
        IntegerProperty minSeconde = new SimpleIntegerProperty(45);
        IntegerProperty maxSeconde = new SimpleIntegerProperty(50);

        CalendarFilter calendarFilter = new CalendarFilter(minAnnee, maxAnnee, minMois, maxMois, minJour, maxJour,
                minHeure, maxHeure, minMinute, maxMinute, minSeconde, maxSeconde);

        assertTrue(calendarFilter.matchTimeFilter("8 h 10 min 45 sec"));
        assertTrue(calendarFilter.matchTimeFilter("22 h 30 min 50 sec"));
        assertTrue(calendarFilter.matchTimeFilter("22 h 30 min 49 sec"));
        assertFalse(calendarFilter.matchTimeFilter("8 h 0 min 0 sec"));
        assertFalse(calendarFilter.matchTimeFilter("22 h 33 min 5 sec"));
    }
}