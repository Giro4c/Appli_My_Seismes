package fr.amu.iut.prototype1.appli_my_seismes;

public class Date {
    private int annee;
    private int mois;
    private int jour;

    public Date(int annee, int mois, int jour) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }

    @Override
    public String toString(){
        return ""+annee+"/"+mois+"/"+"jour";
    }

}
