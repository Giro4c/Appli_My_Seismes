package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleFilterTest {

    @Test
    void testBindingsFilter(){
        StringProperty minProp = new SimpleStringProperty("45.5");
        StringProperty maxProp = new SimpleStringProperty("100.2");
        DoubleFilter doubleFilter = new DoubleFilter(minProp, maxProp);
        // Tests initialisations de min et max
        assertEquals("45.5", doubleFilter.getMin());
        assertEquals("100.2", doubleFilter.getMax());
        // Tests changement de min et max via binding
        minProp.setValue("12,8");
        maxProp.setValue("43.4");
        assertEquals("12,8", doubleFilter.getMin());
        assertEquals("43.4", doubleFilter.getMax());
    }

    @Test
    void matchFilter() {

        StringProperty minProp = new SimpleStringProperty("");
        StringProperty maxProp = new SimpleStringProperty("");
        DoubleFilter doubleFilter = new DoubleFilter(minProp, maxProp);

        // Avec Min et Max vides
        assertTrue(doubleFilter.matchFilter(45.5));
        assertTrue(doubleFilter.matchFilter(null));

        // Avec Min et Max déterminés et en tant que nombres décimaux avec ","
        minProp.setValue("45,5");
        maxProp.setValue("100,2");
        assertTrue(doubleFilter.matchFilter(45.5));
        assertTrue(doubleFilter.matchFilter(100.2));
        assertTrue(doubleFilter.matchFilter(62.8));
        assertFalse(doubleFilter.matchFilter(45.1));
        assertFalse(doubleFilter.matchFilter(100.25));
        assertFalse(doubleFilter.matchFilter(null));

        // Avec Min et Max déterminés et en tant que nombres décimaux avec "."
        minProp.setValue("45.5");
        maxProp.setValue("100.2");
        assertTrue(doubleFilter.matchFilter(45.5));
        assertTrue(doubleFilter.matchFilter(100.2));
        assertTrue(doubleFilter.matchFilter(62.8));
        assertFalse(doubleFilter.matchFilter(45.1));
        assertFalse(doubleFilter.matchFilter(100.25));
        assertFalse(doubleFilter.matchFilter(null));

        // Avec Max invalide
        maxProp.setValue("inval");
        assertTrue(doubleFilter.matchFilter(45.5));
        assertTrue(doubleFilter.matchFilter(100.25));
        assertFalse(doubleFilter.matchFilter(45.1));
        assertFalse(doubleFilter.matchFilter(null));

        // Avec Min et Max invalides
        minProp.setValue("inval");
        assertTrue(doubleFilter.matchFilter(45.5));
        assertTrue(doubleFilter.matchFilter(100.25));
        assertTrue(doubleFilter.matchFilter(45.1));
        assertFalse(doubleFilter.matchFilter(null));

        // Avec Min invalide
        maxProp.setValue("100.2");
        assertTrue(doubleFilter.matchFilter(45.5));
        assertFalse(doubleFilter.matchFilter(100.25));
        assertTrue(doubleFilter.matchFilter(45.1));
        assertFalse(doubleFilter.matchFilter(null));

    }
}