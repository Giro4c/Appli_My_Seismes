package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerOverview {

    private static ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    @FXML
    private BarChart<String, Number> mostAffectedChart;

    @FXML
    private BarChart<String, Number> leastAffectedChart;

    @FXML
    private CategoryAxis xAxisMostAffected;

    @FXML
    private NumberAxis yAxisMostAffected;

    @FXML
    private CategoryAxis xAxisLeastAffected;

    @FXML
    private NumberAxis yAxisLeastAffected;


    @FXML
    private PieChart Pie;

    public void initialize() {

        // Créer une liste pour stocker les régions épicentrales
        List<String> regionsEpicentrales = listeSeisme.stream()
                .map(Seisme::getRegion)
                .distinct()
                .collect(Collectors.toList());


        //barchart


        List<String> regionsByMostAffected = listeSeisme.stream()
                .collect(Collectors.groupingBy(Seisme::getRegion, Collectors.averagingDouble(Seisme::getIntensite)))
                .entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> -entry.getValue()))
                .limit(5)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        // Trier les régions par intensité de séisme (du moins touché au plus touché)
        List<String> regionsByLeastAffected = listeSeisme.stream()
                .collect(Collectors.groupingBy(Seisme::getRegion, Collectors.averagingDouble(Seisme::getIntensite)))
                .entrySet().stream()
                .sorted(Comparator.comparingDouble(entry -> entry.getValue()))
                .limit(5)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());


        // Créer les séries de données pour les graphiques
        XYChart.Series<String, Number> mostAffectedSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> leastAffectedSeries = new XYChart.Series<>();

        // Ajouter les données aux séries de données
        for (String region : regionsByMostAffected) {
            mostAffectedSeries.getData().add(new XYChart.Data<>(region, getAverageIntensityByRegion(listeSeisme, region)));
        }

        for (String region : regionsByLeastAffected) {
            leastAffectedSeries.getData().add(new XYChart.Data<>(region, getAverageIntensityByRegion(listeSeisme, region)));
        }

        // Ajouter les séries de données aux graphiques
        mostAffectedChart.getData().add(mostAffectedSeries);
        leastAffectedChart.getData().add(leastAffectedSeries);

        // Définir les axes des graphiques
        mostAffectedChart.setCategoryGap(20);
        mostAffectedChart.setBarGap(5);
        mostAffectedChart.setLegendVisible(false);
        mostAffectedChart.setTitle("Intensité moyenne des Régions les plus touchées");

        leastAffectedChart.setCategoryGap(20);
        leastAffectedChart.setBarGap(5);
        leastAffectedChart.setLegendVisible(false);
        leastAffectedChart.setTitle("Intensité moyenne des Régions les moins touchées");



        //PIE


        // Créer une liste pour stocker le nombre de séismes par région
        List<Long> nombreSeismesParRegion = regionsEpicentrales.stream()
                .map(region -> listeSeisme.stream()
                        .filter(seisme -> seisme.getRegion().equals(region))
                        .count())
                .collect(Collectors.toList());

        // Définir un seuil pour la catégorie "autres" des régions moins fréquentes
        long seuil = 80; // Seuil

        // Créer une liste pour stocker les données du diagramme circulaire (camembert)
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Parcourir les régions et le nombre de séismes correspondant
        for (int i = 0; i < regionsEpicentrales.size(); i++) {
            String region = regionsEpicentrales.get(i);
            long nombreSeismes = nombreSeismesParRegion.get(i);

            // Vérifier si le nombre de séismes est inférieur au seuil
            if (nombreSeismes < seuil) {
                // Ajouter le nombre de séismes à la catégorie "Autres"
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

                // Ajouter la région et le pourcentage dans le texte de la légende
                PieChart.Data data = new PieChart.Data(region + " (" + String.format("%.2f", pourcentage) + "%)", nombreSeismes);
                pieChartData.add(data);




            }


            // Définir les données du Pie
            Pie.setData(pieChartData);

        }






    }



    // Méthode pour calculer le total des séismes
    private long getTotalSeismes(List<Long> nombreSeismesParRegion) {
        return nombreSeismesParRegion.stream()
                .mapToLong(Long::longValue)
                .sum();
    }


    private double getAverageIntensityByRegion(List<Seisme> listeSeisme, String region) {
        // Calculer la moyenne d'intensité pour une région donnée
        List<Seisme> seismesByRegion = listeSeisme.stream()
                .filter(seisme -> seisme.getRegion().equals(region))
                .collect(Collectors.toList());

        double sumIntensity = seismesByRegion.stream()
                .mapToDouble(Seisme::getIntensite)
                .sum();

        return sumIntensity / seismesByRegion.size();
    }




}





















