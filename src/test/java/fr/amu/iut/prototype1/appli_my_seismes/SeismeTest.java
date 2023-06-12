package fr.amu.iut.prototype1.appli_my_seismes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeismeTest {

    @Test
    void testEquals() {
        Seisme seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        Seisme seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(null, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertFalse(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(null, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(null, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", null, "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", null, "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", null, "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", null, "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", null,
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", null,
                478075.43, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                null, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                null, 6509611.52, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, null, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, null, 45.65, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, null, 0.15, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, null, 0.15, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, null, 7.0, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, null, 7.0, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, null, "INFORMATION ISOLÉE");
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, null, "INFORMATION ISOLÉE");
        assertTrue(seismeExpected.equals(seismeTest));
        seismeExpected = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, null);
        seismeTest = new Seisme(160001, "1014/", "", "ANGOUMOIS (ANGOULEME)", "CHARENTES", "",
                478075.43, 6509611.52, 45.65, 0.15, 7.0, null);
        assertTrue(seismeExpected.equals(seismeTest));
    }

}