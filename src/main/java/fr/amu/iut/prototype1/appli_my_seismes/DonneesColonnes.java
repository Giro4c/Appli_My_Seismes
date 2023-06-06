package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DonneesColonnes {
    private final StringProperty attribute1;
    private final StringProperty attribute2;
    private final StringProperty attribute3;

    public DonneesColonnes(String attribute1, String attribute2, String attribute3) {
        this.attribute1 = new SimpleStringProperty(attribute1);
        this.attribute2 = new SimpleStringProperty(attribute2);
        this.attribute3 = new SimpleStringProperty(attribute3);
    }

    public String getAttribute1() {
        return attribute1.get();
    }

    public StringProperty attribute1Property() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1.set(attribute1);
    }

    public String getAttribute2() {
        return attribute2.get();
    }

    public StringProperty attribute2Property() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2.set(attribute2);
    }

    public String getAttribute3() {
        return attribute3.get();
    }

    public StringProperty attribute3Property() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3.set(attribute3);
    }
}
