package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerOverview {

    private static ArrayList<Seisme> listeSeisme = CSVReader.StringArrayToSeismeArrayList(
            CSVReader.CSVFileReader("src/main/resources/fr/amu/iut/prototype1/appli_my_seismes/SisFrance_seismes_20230604151458.csv"));
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private PieChart Pie;

    public void initialize() {

   //barchart



        // Créer une liste pour stocker les régions épicentrales
        List<String> regionsEpicentrales = listeSeisme.stream()
                .map(Seisme::getRegion)
                .distinct()
                .collect(Collectors.toList());

// Créer une liste pour stocker les moyennes d'intensité épicentrale par région
        List<Double> moyennesIntensiteEpicentrale = regionsEpicentrales.stream()
                .map(region -> listeSeisme.stream()
                        .filter(seisme -> seisme.getRegion().equals(region))
                        .mapToDouble(Seisme::getIntensite)
                        .average()
                        .orElse(0.0)) // Valeur par défaut si la moyenne n'est pas calculée
                .collect(Collectors.toList());




        // Crée une série de données à partir des listes xData et yData
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (int i = 0; i < regionsEpicentrales.size(); i++) {
            series.getData().add(new XYChart.Data<>(regionsEpicentrales.get(i), moyennesIntensiteEpicentrale.get(i)));
        }

        // Ajoute la série de données au BarChart
        barChart.getData().add(series);


        //pie



        // Créer une liste pour stocker le nombre de séismes par région
        List<Long> nombreSeismesParRegion = regionsEpicentrales.stream()
                .map(region -> listeSeisme.stream()
                        .filter(seisme -> seisme.getRegion().equals(region))
                        .count())
                .collect(Collectors.toList());

        // Créer les données pour le diagramme circulaire (camembert)
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (int i = 0; i < regionsEpicentrales.size(); i++) {
            String region = regionsEpicentrales.get(i);
            long nombreSeismes = nombreSeismesParRegion.get(i);
            pieChartData.add(new PieChart.Data(region, nombreSeismes));
        }

        // Créer le diagramme circulaire (camembert)
        Pie.setData(pieChartData);
        Pie.setTitle("Nombre de séismes par région");


    }



    }





















