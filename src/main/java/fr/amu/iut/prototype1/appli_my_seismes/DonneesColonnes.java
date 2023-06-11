package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Classe utilisée dans l'affichage des données dans une TableView de 12 colonnes. Représente une ligne de tableau et la valeur StringProperty
 * à chaque colonne.
 * @deprecated Remplacée par des StringProperties dans Seismes qui permettent d'utiliser directement les attributs dans Séismes sans passer par
 * une classe intermédiare.
 * @see Seisme
 */
public class DonneesColonnes {
    private final StringProperty attribute1;
    private final StringProperty attribute2;
    private final StringProperty attribute3;
    private final StringProperty attribute4;
    private final StringProperty attribute5;
    private final StringProperty attribute6;

    private final StringProperty attribute7;

    private final StringProperty attribute8;

    private final StringProperty attribute9;

    private final StringProperty attribute10;

    private final StringProperty attribute11;

    private final StringProperty attribute12;

    public DonneesColonnes(String attribute1, String attribute2, String attribute3, String attribute4, String attribute5, String attribute6, String attribute7, String attribute8, String attribute9, String attribute10, String attribute11, String attribute12) {
        this.attribute1 = new SimpleStringProperty(attribute1);
        this.attribute2 = new SimpleStringProperty(attribute2);
        this.attribute3 = new SimpleStringProperty(attribute3);
        this.attribute4 = new SimpleStringProperty(attribute4);
        this.attribute5 = new SimpleStringProperty(attribute5);
        this.attribute6 = new SimpleStringProperty(attribute6);
        this.attribute7 = new SimpleStringProperty(attribute7);
        this.attribute8 = new SimpleStringProperty(attribute8);
        this.attribute9 = new SimpleStringProperty(attribute9);
        this.attribute10 = new SimpleStringProperty(attribute10);
        this.attribute11 = new SimpleStringProperty(attribute11);
        this.attribute12 = new SimpleStringProperty(attribute12);
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


    public String getAttribute4() {
        return attribute4.get();
    }

    public StringProperty attribute4Property() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) { this.attribute4.set(attribute4);
    }



    public String getAttribute5() {
        return attribute5.get();
    }

    public StringProperty attribute5Property() {
        return attribute5;
    }

    public void setAttribute5(String attribute3) {
        this.attribute5.set(attribute3);
    }



    public String getAttribute6() {
        return attribute6.get();
    }

    public StringProperty attribute6Property() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6.set(attribute6);
    }



    public String getAttribute7() {
        return attribute7.get();
    }

    public StringProperty attribute7Property() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7.set(attribute7);
    }

    public String getAttribute8() {
        return attribute8.get();
    }

    public StringProperty attribute8Property() {
        return attribute8;
    }

    public void setAttribute8(String attribute8) {
        this.attribute8.set(attribute8);
    }

    public String getAttribute9() {
        return attribute9.get();
    }

    public StringProperty attribute9Property() {
        return attribute9;
    }

    public void setAttribute9(String attribute9) {
        this.attribute9.set(attribute9);
    }


    public String getAttribute10() {
        return attribute10.get();
    }

    public StringProperty attribute10Property() {
        return attribute10;
    }

    public void setAttribute10(String attribute10) { this.attribute10.set(attribute10);
    }



    public String getAttribute11() {
        return attribute11.get();
    }

    public StringProperty attribute11Property() {
        return attribute11;
    }

    public void setAttribute11(String attribute11) {
        this.attribute11.set(attribute11);
    }



    public String getAttribute12() {
        return attribute12.get();
    }

    public StringProperty attribute12Property() {
        return attribute12;
    }

    public void setAttribute12(String attribute12) {
        this.attribute12.set(attribute12);
    }

}
