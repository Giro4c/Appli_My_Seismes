package fr.amu.iut.prototype1.appli_my_seismes.datas.filters;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerFilterTest {

    @Test
    void testBindingsFilter(){
        StringProperty minProp = new SimpleStringProperty("45");
        StringProperty maxProp = new SimpleStringProperty("100");
        IntegerFilter integerFilter = new IntegerFilter(minProp, maxProp);
        // Tests initialisations de min et max
        assertEquals("45", integerFilter.getMin());
        assertEquals("100", integerFilter.getMax());
        // Tests changement de min et max via binding
        minProp.setValue("12");
        maxProp.setValue("43");
        assertEquals("12", integerFilter.getMin());
        assertEquals("43", integerFilter.getMax());
    }

    @Test
    void matchFilter() {

        StringProperty minProp = new SimpleStringProperty("");
        StringProperty maxProp = new SimpleStringProperty("");
        IntegerFilter integerFilter = new IntegerFilter(minProp, maxProp);

        // Avec Min et Max vides
        assertTrue(integerFilter.matchFilter(45));
        assertTrue(integerFilter.matchFilter(null));

        // Avec Min et Max déterminés et en tant que nombres
        minProp.setValue("45");
        maxProp.setValue("100");
        assertTrue(integerFilter.matchFilter(45));
        assertTrue(integerFilter.matchFilter(100));
        assertTrue(integerFilter.matchFilter(62));
        assertFalse(integerFilter.matchFilter(40));
        assertFalse(integerFilter.matchFilter(110));
        assertFalse(integerFilter.matchFilter(null));

        // Avec Max invalide
        maxProp.setValue("inval");
        assertTrue(integerFilter.matchFilter(45));
        assertTrue(integerFilter.matchFilter(110));
        assertFalse(integerFilter.matchFilter(40));
        assertFalse(integerFilter.matchFilter(null));

        // Avec Min et Max invalides
        minProp.setValue("inval");
        assertTrue(integerFilter.matchFilter(45));
        assertTrue(integerFilter.matchFilter(110));
        assertTrue(integerFilter.matchFilter(40));
        assertFalse(integerFilter.matchFilter(null));

        // Avec Min invalide
        maxProp.setValue("100");
        assertTrue(integerFilter.matchFilter(45));
        assertFalse(integerFilter.matchFilter(110));
        assertTrue(integerFilter.matchFilter(40));
        assertFalse(integerFilter.matchFilter(null));

    }
}