package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerOverview {

    private static ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    @FXML
    private StackedBarChart<String, Number> mostAffectedChart;


    @FXML
    private CategoryAxis xAxisMostAffected;

    @FXML
    private NumberAxis yAxisMostAffected;

    @FXML
    private BarChart<String, Integer> intensityChart;

    @FXML
    private CategoryAxis xAxisIntensity;

    @FXML
    private NumberAxis yAxisIntensity;



    @FXML
    private PieChart Pie;

    @FXML
    private PieChart PieQualite;

    @FXML
    private Label seismeTotaux;

    @FXML
    private Label regionPlusAffecte;

    @FXML
    private Label regionMoinsAffecte;

    @FXML
    private Label seismePlusIntense;

    @FXML
    private Label regionTotaux;

    @FXML
    private Label totalSeismeSuperieurA5;

    @FXML
    private Label IntensiteMax;

    @FXML
    private Label IntensiteMoy;

    @FXML
    private Label IntensiteMin;

    @FXML
    private Label IntensiteMediane;

    @FXML
    private Label IntensiteEcartype;

    @FXML
    private Label IntensiteEtendu;

    @FXML
    private NumberAxis xScatterAxis;
    @FXML
    private NumberAxis yScatterAxis;
    @FXML
    private ScatterChart<Number, Number> scatterChart;

    private List<String> regionsByMostAffected;
    private List<String> regionsEpicentrales;



    public void initialize() {

        // liste pour stocker les régions
        regionsEpicentrales = listeSeisme.stream()
                .map(Seisme::getRegion)
                .distinct()
                .collect(Collectors.toList());


        //regions les plus affecté dans l'ordre (list pour le stacked)
        regionsByMostAffected = listeSeisme.stream()
                .collect(Collectors.groupingBy(Seisme::getRegion,
                        Collectors.mapping(Seisme::getIntensite, Collectors.toList())))
                .entrySet().stream()
                .sorted(Comparator.comparingLong(entry -> -entry.getValue().size()))
                .limit(17)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());


        // Donnees page 1
        setUpDatasTab();

        // stackedbarchart
        setUpBarChart();

        //PIE 1
        setUpFirstPie();

        //PIE 2 (qualité epicentrale)
        setUpSecondPie();

        // ScatterChart
        setUpScatterChart();

    }

    /**
     * Configure tous les labels de l'onglet des données statistiques (Onglet 1 de l'Overview)
     */
    private void setUpDatasTab() {

        //seisme Totaux
        seismeTotaux.setText(String.valueOf(listeSeisme.size()));


        //region la plus affecté
        regionPlusAffecte.setText(regionsByMostAffected.get(0));

        //region la moins affecté
        List<String> regionsByLeastAffected = new ArrayList<>(regionsByMostAffected);
        Collections.reverse(regionsByLeastAffected);
        regionMoinsAffecte.setText(regionsByLeastAffected.get(0));


        //date et region ayant subi intensité max
        double maxIntensite = listeSeisme.stream()
                .mapToDouble(Seisme::getIntensite)
                .max()
                .orElse(Double.NaN);

        List<Seisme> seismesMaxIntensite = listeSeisme.stream()
                .filter(seisme -> seisme.getIntensite() == maxIntensite)
                .collect(Collectors.toList());

        String result;
        if (seismesMaxIntensite.isEmpty()) {
            result = "Aucun séisme enregistré";
        } else {
            result = seismesMaxIntensite.stream()
                    .map(seisme -> "Date : " + seisme.dateProperty().get() + ", Région : " + seisme.getRegion())
                    .collect(Collectors.joining("\n"));
        }

        seismePlusIntense.setText(result);

        //date et region du seisme le plus intense

        //Nombre de seisme a intensité superieur a 5
        long nombreSeismesSup5 = listeSeisme.stream()
                .filter(seisme -> seisme.getIntensite() > 5)
                .count();
        totalSeismeSuperieurA5.setText(Long.toString(nombreSeismesSup5));

        // Liste des intensités des séismes (pour le code)
        List<Double> intensites = listeSeisme.stream()
                .map(Seisme::getIntensite)
                .filter(intensite -> intensite != Seisme.DEFAULT_INVALID_INTENSITE)
                .collect(Collectors.toList());


        //intensité min
        double minIntensite = listeSeisme.stream()
                .mapToDouble(Seisme::getIntensite)
                .min()
                .orElse(Double.NaN);
        IntensiteMin.setText("Minimum : " + minIntensite);


        //Intensité moyenne
        // Calculer la moyenne de l'intensité totale
        double moyenneIntensite = listeSeisme.stream()
                .mapToDouble(Seisme::getIntensite)
                .average()
                .orElse(Double.NaN);
        IntensiteMoy.setText(", Moyenne  : " + moyenneIntensite);


        //Intensité max
        IntensiteMax.setText(", Maximum : " + maxIntensite);


        // statistiques sur l'intensité
        DoubleSummaryStatistics statistiques = intensites.stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();


        // mediane de l'intensité
        double mediane;
        if (intensites.size() % 2 == 0) {
            int index1 = (intensites.size() / 2) - 1;
            int index2 = index1 + 1;
            mediane = (intensites.get(index1) + intensites.get(index2)) / 2.0;
        } else {
            int index = intensites.size() / 2;
            mediane = intensites.get(index);
        }
        IntensiteMediane.setText(", Médiane : " + mediane);


        // ecart type de l'intensité
        double sommeCarres = intensites.stream()
                .mapToDouble(i -> Math.pow(i - statistiques.getAverage(), 2))
                .sum();
        double ecartType = Math.sqrt(sommeCarres / intensites.size());
        IntensiteEcartype.setText(", Ecart - type : " + ecartType);


        // étendue de l'intensité
        double etendue = statistiques.getMax() - statistiques.getMin();
        IntensiteEtendu.setText(", Etendue " + etendue);


        //Nombre de regions
        regionTotaux.setText(Integer.toString(regionsEpicentrales.size()));

    }







    /**
     * Configure et remplit le graphique en barre de l'Overview.
     */
    private void setUpBarChart(){


        XYChart.Series<String, Number> mostAffectedSeries1 = new XYChart.Series<>();
        XYChart.Series<String, Number> mostAffectedSeries2 = new XYChart.Series<>();
        XYChart.Series<String, Number> mostAffectedSeries3 = new XYChart.Series<>();

        for (String region : regionsByMostAffected) {
            List<Double> intensiteValues = listeSeisme.stream()
                    .filter(seisme -> seisme.getRegion().equals(region))
                    .map(Seisme::getIntensite)
                    .collect(Collectors.toList());

            DoubleSummaryStatistics statistics = intensiteValues.stream()
                    .mapToDouble(Double::doubleValue)
                    .summaryStatistics();

            List<Number> values = Arrays.asList(statistics.getMin(), statistics.getAverage(), statistics.getMax());


            mostAffectedSeries1.getData().add(new XYChart.Data<>(region, values.get(0))); // Valeur minimale
            mostAffectedSeries2.getData().add(new XYChart.Data<>(region, values.get(1))); // Valeur moyenne
            mostAffectedSeries3.getData().add(new XYChart.Data<>(region, values.get(2))); // Valeur maximale
        }

        mostAffectedChart.getData().addAll(mostAffectedSeries1, mostAffectedSeries2, mostAffectedSeries3);
        mostAffectedChart.setTitle("Minimum, moyenne et maximum de l'intensité des régions les plus touchés");




    }

    /**
     * Rempli le premier graphique en camenbert de l'Overview
     */
    private void setUpFirstPie(){
        // Créer une liste pour stocker le nombre de séismes par région
        List<Long> nombreSeismesParRegion = regionsEpicentrales.stream()
                .map(region -> listeSeisme.stream()
                        .filter(seisme -> seisme.getRegion().equals(region))
                        .count())
                .collect(Collectors.toList());

        // Définir un seuil pour la catégorie "autres" des régions moins fréquentes
        long seuil = 80; // Seuil

        // Créer une liste pour stocker les données du pie
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Parcourir les régions et le nombre de séismes correspondant
        for (int i = 0; i < regionsEpicentrales.size(); i++) {
            String region = regionsEpicentrales.get(i);
            long nombreSeismes = nombreSeismesParRegion.get(i);

            // verifie si en dessous du seuil
            if (nombreSeismes < seuil) {
                // Ajout du nombre de seismes a autres
                PieChart.Data autresData = pieChartData.stream()
                        .filter(data -> data.getName().equals("Autres"))
                        .findFirst()
                        .orElse(null);

                if (autresData == null) {
                    autresData = new PieChart.Data("Autres", nombreSeismes);
                    pieChartData.add(autresData);
                }
                else {
                    autresData.setPieValue(autresData.getPieValue() + nombreSeismes);
                }

            }
            else {
                //pourcentage
                double pourcentage = (nombreSeismes / (double) getTotalSeismes(nombreSeismesParRegion)) * 100;

                // Ajoute pourcentage ds legende
                PieChart.Data data = new PieChart.Data(region + " (" + String.format("%.2f", pourcentage) + "%)", nombreSeismes);
                pieChartData.add(data);
            }

            // Définir les données du Pie
            Pie.setData(pieChartData);
            Pie.setTitle("Répartiton des séismes en fonction des régions");

        }
    }

    /**
     * Rempli le deuxième graphique en camenbert de l'Overview
     */
    private void setUpSecondPie(){
        //liste pour stocker la qualité épicentrale des séismes
        List<String> qualitesEpicentrales = listeSeisme.stream()
                .map(Seisme::getQualiteIntensiteEpicentre)
                .distinct()
                .collect(Collectors.toList());

        // liste pour stocker le nombre de seisme par qualité
        List<Long> nombreSeismesParQualite = qualitesEpicentrales.stream()
                .map(qualite -> listeSeisme.stream()
                        .filter(seisme -> seisme.getQualiteIntensiteEpicentre().equals(qualite))
                        .count())
                .collect(Collectors.toList());

        // liste pour stocker les donnee du pie
        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();

        long totalSeismes = nombreSeismesParQualite.stream().mapToLong(Long::longValue).sum();

        // Parcourir les qualités épicentrales et le nombre de séismes correspondant
        for (int i = 0; i < qualitesEpicentrales.size(); i++) {
            String qualite = qualitesEpicentrales.get(i);
            long nombreSeismes = nombreSeismesParQualite.get(i);

            double pourcentage = (nombreSeismes / (double) totalSeismes) * 100;

            // Ajouter les donnees au pie chart
            PieChart.Data data = new PieChart.Data(qualite + " (" + String.format("%.1f", pourcentage) + "%)", nombreSeismes);
            pieChartData2.add(data);



        }

        // Définir les données du pie chart
        PieQualite.setData(pieChartData2);
        PieQualite.setTitle("Répartition des qu épicentrale");

    }

    /**
     * Rempli le graphique en barre du nombre de séismes en fonction de la plage d'intensité.
     */
    private void setUpBarChartIntervalles(){
        List<String> intensityIntervals = Arrays.asList("0-1", "1-2", "2-3", "3-4", "4-5", "5-6", "6-7", "7-8", "8-9", "9+");

        List<Integer> seismesParIntensite = new ArrayList<>(Collections.nCopies(intensityIntervals.size(), 0));

        for (Seisme seisme : listeSeisme) {
            double intensite = seisme.getIntensite();
            if (intensite != Seisme.DEFAULT_INVALID_INTENSITE) {
                for (int i = 0; i < intensityIntervals.size() - 1; i++) {
                    String[] interval = intensityIntervals.get(i).split("-");
                    double lowerBound = Double.parseDouble(interval[0]);
                    double upperBound = Double.parseDouble(interval[1]);
                    if (intensite >= lowerBound && intensite < upperBound) {
                        seismesParIntensite.set(i, seismesParIntensite.get(i) + 1);
                        break;
                    }
                }
                if (intensite >= 9) {
                    seismesParIntensite.set(intensityIntervals.size() - 1, seismesParIntensite.get(intensityIntervals.size() - 1) + 1);
                }
            }
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < intensityIntervals.size(); i++) {
            String interval = intensityIntervals.get(i);
            int count = seismesParIntensite.get(i);
            series.getData().add(new XYChart.Data<>(interval, count));
        }

        intensityChart.getData().add(series);

        intensityChart.setTitle("Nombre de séismes par intervalle d'intensité");
        xAxisIntensity.setLabel("Intervalle d'intensité");
        yAxisIntensity.setLabel("Nombre de séismes");


    }

    /**
     * Rempli l'unique scatter chart de l'Overview : Nombre de séismes en fonction de l'année sur un siècle au maximum.
     */
    private void setUpScatterChart(){

//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("Intensitées");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Nombre de séismes");

        Integer[] bornesAnnee = latestYearSeisme(listeSeisme);
        int maxAnnee = bornesAnnee[1];
        int minAnnee = bornesAnnee[0];

        int limiteIntervalle = 100;
        if (bornesAnnee != null && (maxAnnee - minAnnee) > limiteIntervalle) {
            minAnnee = maxAnnee - limiteIntervalle;
        }

        // Changement des limites d'intervalles du graphique
        xScatterAxis.lowerBoundProperty().setValue(minAnnee);
        xScatterAxis.upperBoundProperty().setValue(bornesAnnee[1]);
        // Stockeur des compteurs de séismes
        Integer[] countSeismesAnnee = new Integer[maxAnnee - minAnnee + 1];

        for (Seisme seisme : listeSeisme) {
            if (seisme.getCalendar().getAnnee() == null || seisme.getIntensite() == null) continue;
            if (seisme.getCalendar().getAnnee() < minAnnee) continue;
//            series1.getData().add(new XYChart.Data(seisme.getCalendar().getAnnee(), seisme.getIntensite()));
            if (countSeismesAnnee[seisme.getCalendar().getAnnee() - minAnnee] == null) {
                countSeismesAnnee[seisme.getCalendar().getAnnee() - minAnnee] = 1;
            } else {
                ++countSeismesAnnee[seisme.getCalendar().getAnnee() - minAnnee];
            }
        }

        // Remplissage de la serie 2
        for (int annee = 0; annee < countSeismesAnnee.length; ++annee){
            if (countSeismesAnnee[annee] == null){
                series2.getData().add(new XYChart.Data((bornesAnnee[0] + annee), 0));
            }
            else {
                series2.getData().add(new XYChart.Data((bornesAnnee[0] + annee), countSeismesAnnee[annee]));
            }
        }

        // Ajout des séries au graphique en scatter
        scatterChart.getData().setAll(/*series1,*/ series2);
    }

    /**
     * Détermine quels sont : l'année la plus grande et l'année la plus petite dans une liste de seismes
     * @param listeSeisme La liste de séisme dont on détermine l'année la plus grande et l'année la plus petite.
     * @return Renvoie un Integer[] de taille 2 avec l'année minimale (0) et l'année maximal (1) sauf si la liste de séismes est null. Renvoie null dans ce cas là.
     */
    private Integer[] latestYearSeisme(ArrayList<Seisme> listeSeisme){
        if (listeSeisme != null && listeSeisme.size() > 1){
            int index = 0;
            Integer maxYear = null;
            Integer minYear = null;
            // initialise min et max année
            while((maxYear == null && minYear == null) && index < listeSeisme.size()){
                if (listeSeisme.get(index).getCalendar().getAnnee() != null){
                    maxYear = listeSeisme.get(index).getCalendar().getAnnee();
                    minYear = listeSeisme.get(index).getCalendar().getAnnee();
                }
                ++index;
            }
            while(index < listeSeisme.size()){
                if (listeSeisme.get(index).getCalendar().getAnnee() != null){
                    if (listeSeisme.get(index).getCalendar().getAnnee() > maxYear) {
                        maxYear = listeSeisme.get(index).getCalendar().getAnnee();
                    }
                    else if (listeSeisme.get(index).getCalendar().getAnnee() < minYear) {
                        minYear = listeSeisme.get(index).getCalendar().getAnnee();
                    }
                }
                ++index;
            }
            Integer[] bornesAnnee = {minYear, maxYear};
            return bornesAnnee;
        }
        else if (listeSeisme.size() == 1){
            Integer[] bornesAnnee = {listeSeisme.get(0).getCalendar().getAnnee(), listeSeisme.get(0).getCalendar().getAnnee()};
            return bornesAnnee;
        }
        else return null;
    }


    /**
     * Méthode pour calculer le total des séismes
     * @param nombreSeismesParRegion
     * @return long - Nombre total de séisme
     */
    private long getTotalSeismes(List<Long> nombreSeismesParRegion) {
        return nombreSeismesParRegion.stream()
                .mapToLong(Long::longValue)
                .sum();
    }


//    private double getAverageIntensityByRegion(List<Seisme> listeSeisme, String region) {
//        // Calculer la moyenne d'intensité pour une région
//        List<Seisme> seismesByRegion = listeSeisme.stream()
//                .filter(seisme -> seisme.getRegion().equals(region))
//                .collect(Collectors.toList());
//
//        double sumIntensity = seismesByRegion.stream()
//                .mapToDouble(Seisme::getIntensite)
//                .sum();
//
//        return sumIntensity / seismesByRegion.size();
//    }




}

