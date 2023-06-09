package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilitaireTest {

    @Test
    void richterColor() {
        // Test pour intensite == 0
        assertEquals(Color.rgb(255, 255, 13), Utilitaire.richterColor(0));
        // Test pour intensite < 0
        assertEquals(Color.rgb(255, 255, 13), Utilitaire.richterColor(-5));
        // Test pour intensite == 10
        assertEquals(Color.rgb(255, 13, 13), Utilitaire.richterColor(10));
        // Test pour intensite > 10
        assertEquals(Color.rgb(255, 13, 13), Utilitaire.richterColor(11));
        // Test pour intensite où (24.2 * intensite) se termine par .0
        assertEquals(Color.rgb(255, 134, 13), Utilitaire.richterColor(5));
        // Test pour intensite où (24.2 * intensite) se termine par .2
        assertEquals(Color.rgb(255, 110, 13), Utilitaire.richterColor(6));
        // Test pour intensite où (24.2 * intensite) se termine par .5
        assertEquals(Color.rgb(255, 74, 13), Utilitaire.richterColor(7.5));
        // Test pour intensite où (24.2 * intensite) se termine par .6
        assertEquals(Color.rgb(255, 62, 13), Utilitaire.richterColor(8));
    }
}