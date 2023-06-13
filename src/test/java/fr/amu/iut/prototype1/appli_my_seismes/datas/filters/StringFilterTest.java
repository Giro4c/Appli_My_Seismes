package fr.amu.iut.prototype1.appli_my_seismes.datas.filters;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringFilterTest {

    @Test
    void testBindingsFilter(){
        StringProperty debProp = new SimpleStringProperty("Hello");
        StringProperty finProp = new SimpleStringProperty("world !");
        StringProperty conProp = new SimpleStringProperty("lo wo");
        StringFilter stringFilter = new StringFilter(debProp, finProp, conProp);
        // Tests initialisations de debut, fin et contained
        assertEquals("Hello", stringFilter.getDebut());
        assertEquals("world !", stringFilter.getFin());
        assertEquals("lo wo", stringFilter.getContained());
        // Tests changement de debut, fin et contained via binding
        debProp.setValue("Bonjour");
        finProp.setValue("monde !");
        conProp.setValue("jour mon");
        assertEquals("Bonjour", stringFilter.getDebut());
        assertEquals("monde !", stringFilter.getFin());
        assertEquals("jour mon", stringFilter.getContained());

    }

    @Test
    void matchDebut() {
        StringProperty debProp = new SimpleStringProperty("Hello");
        StringProperty finProp = new SimpleStringProperty("world !");
        StringProperty conProp = new SimpleStringProperty("lo wo");
        StringFilter stringFilter = new StringFilter(debProp, finProp, conProp);

        // Test quand debut n'est pas vide
        assertTrue(stringFilter.matchDebut("Hello world !"));
        assertTrue(stringFilter.matchDebut("HELLO WORLD !"));
        assertTrue(stringFilter.matchDebut("Hello"));
        assertFalse(stringFilter.matchDebut("Hella"));
        assertFalse(stringFilter.matchDebut("Bonjour monde !"));
        assertFalse(stringFilter.matchDebut(""));
        assertFalse(stringFilter.matchDebut(null));

        // Test quand debut est vide
        debProp.setValue("");
        assertTrue(stringFilter.matchDebut("Hello world !"));
        assertTrue(stringFilter.matchDebut(""));
        assertTrue(stringFilter.matchDebut(null));
    }

    @Test
    void matchFin() {
        StringProperty debProp = new SimpleStringProperty("Hello");
        StringProperty finProp = new SimpleStringProperty("world !");
        StringProperty conProp = new SimpleStringProperty("lo wo");
        StringFilter stringFilter = new StringFilter(debProp, finProp, conProp);

        // Test quand fin n'est pas vide
        assertTrue(stringFilter.matchFin("Hello world !"));
        assertTrue(stringFilter.matchFin("HELLO WORLD !"));
        assertTrue(stringFilter.matchFin("world !"));
        assertFalse(stringFilter.matchFin("Morld !"));
        assertFalse(stringFilter.matchFin("world ?"));
        assertFalse(stringFilter.matchFin("Bonjour monde !"));
        assertFalse(stringFilter.matchFin(""));
        assertFalse(stringFilter.matchFin(null));

        // Test quand fin est vide
        finProp.setValue("");
        assertTrue(stringFilter.matchFin("Hello world !"));
        assertTrue(stringFilter.matchFin(""));
        assertTrue(stringFilter.matchFin(null));
    }

    @Test
    void matchContained() {
        StringProperty debProp = new SimpleStringProperty("Hello");
        StringProperty finProp = new SimpleStringProperty("world !");
        StringProperty conProp = new SimpleStringProperty("lo wo");
        StringFilter stringFilter = new StringFilter(debProp, finProp, conProp);

        // Test quand contained n'est pas vide
        assertTrue(stringFilter.matchContained("Hello world !"));
        assertTrue(stringFilter.matchContained("HELLO WORLD !"));
        assertTrue(stringFilter.matchContained("lo wo"));
        assertFalse(stringFilter.matchContained("Bonjour monde !"));
        assertFalse(stringFilter.matchContained(""));
        assertFalse(stringFilter.matchContained(null));

        // Test quand contained est vide
        conProp.setValue("");
        assertTrue(stringFilter.matchContained("Hello world !"));
        assertTrue(stringFilter.matchContained(""));
        assertTrue(stringFilter.matchContained(null));
    }
}