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
    private PieChart Pie;

    @FXML
    private PieChart PieQualite;

    @FXML
    private Label seismeTotaux;

    @FXML
    private Label regionPlusAffecte;

    @FXML
    private Label seismePlusIntense;

    public void initialize() {

        // liste pour stocker les régions
        List<String> regionsEpicentrales = listeSeisme.stream()
                .map(Seisme::getRegion)
                .distinct()
                .collect(Collectors.toList());


        //regions les plus affecté dans l'ordre (list pour le stacked)
        List<String> regionsByMostAffected = listeSeisme.stream()
                .collect(Collectors.groupingBy(Seisme::getRegion,
                        Collectors.mapping(Seisme::getIntensite, Collectors.toList())))
                .entrySet().stream()
                .sorted(Comparator.comparingLong(entry -> -entry.getValue().size()))
                .limit(20)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());


        //Donnees page 1

        //seisme Totaux
        seismeTotaux.setText(String.valueOf(listeSeisme.size()));


        //region la plus affecté
        regionPlusAffecte.setText(regionsByMostAffected.get(0));

        //date et region du seisme le plus intense















        //stackedbarchart




        XYChart.Series<String, Number> mostAffectedSeries = new XYChart.Series<>();

        for (String region : regionsByMostAffected) {
            List<Double> intensiteValues = listeSeisme.stream()
                    .filter(seisme -> seisme.getRegion().equals(region))
                    .map(Seisme::getIntensite)
                    .collect(Collectors.toList());

            DoubleSummaryStatistics statistics = intensiteValues.stream()
                    .mapToDouble(Double::doubleValue)
                    .summaryStatistics();

            List<Number> values = Arrays.asList(statistics.getMin(), statistics.getAverage(), statistics.getMax());

            mostAffectedSeries.getData().add(new XYChart.Data<>(region, values.get(0))); // Valeur minimale
            mostAffectedSeries.getData().add(new XYChart.Data<>(region, values.get(1))); // Valeur moyenne
            mostAffectedSeries.getData().add(new XYChart.Data<>(region, values.get(2))); // Valeur maximale
        }

        mostAffectedChart.getData().add(mostAffectedSeries);




        //PIE


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
                } else {
                    autresData.setPieValue(autresData.getPieValue() + nombreSeismes);
                }
            } else {

                //pourcentage
                double pourcentage = (nombreSeismes / (double) getTotalSeismes(nombreSeismesParRegion)) * 100;

                // Ajoute pourcentage ds legende
                PieChart.Data data = new PieChart.Data(region + " (" + String.format("%.2f", pourcentage) + "%)", nombreSeismes);
                pieChartData.add(data);




            }


            // Définir les données du Pie
            Pie.setData(pieChartData);

        }



        //PIE 2 (qualité epicentrale)
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

        // Parcourir les qualités épicentrales et le nombre de séismes correspondant
        for (int i = 0; i < qualitesEpicentrales.size(); i++) {
            String qualite = qualitesEpicentrales.get(i);
            long nombreSeismes = nombreSeismesParQualite.get(i);

            // Ajouter les données au pie chart
            PieChart.Data data = new PieChart.Data(qualite, nombreSeismes);
            pieChartData2.add(data);
        }

        // Définir les données du pie chart
        PieQualite.setData(pieChartData2);
    }





    //Scatter chart y=intensité x=temps(années)
    List<scatterChartData> scatterChartDataList = listeSeisme.stream()
            .collect(Collectors.groupingBy(seisme -> seisme.getIntensite()))
            .entrySet().stream()
            .sorted(Comparator.comparingDouble(entry -> entry.getKey()))
            .map(entry -> new scatterChartData(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

    ObservableList<scatterChartData> scatterChartDataList = FXCollections.observableArrayList();

    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Durée (en années)");
        yAxis.setLabel("Intensité");

    ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);

    XYChart.Series<Number, Number> chart = new XYChart.Series<>();
        for (scatterChartData data : scatterChartDataList) {
        chart.getData().add(new XYChart.Data<>(data.getDurationYears(), data.getIntensity()));
    }
        scatterChart.getData().add(chart);
}







    // Méthode pour calculer le total des séismes
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

