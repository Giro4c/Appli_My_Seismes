package fr.amu.iut.prototype1.appli_my_seismes;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class ControllerOverview {

    @FXML
    private Button btnHome;

    // Elements graphiques -----------------------------------------------------
        // StackedBarChart mostAffectedChart
    @FXML
    private StackedBarChart<String, Number> mostAffectedChart;
    @FXML
    private CategoryAxis xAxisMostAffected;
    @FXML
    private NumberAxis yAxisMostAffected;
        // BarChart intensityChart
    @FXML
    private BarChart<String, Integer> intensityChart;
    @FXML
    private CategoryAxis xAxisIntensity;
    @FXML
    private NumberAxis yAxisIntensity;
        // Pie Regions
    @FXML
    private PieChart PieRegion;
        // Pie Qualité intensité épicentre
    @FXML
    private PieChart PieQualite;
        // Data Onglet labels
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
        // ScatterChart
    @FXML
    private NumberAxis xScatterAxis;
    @FXML
    private NumberAxis yScatterAxis;
    @FXML
    private ScatterChart<Number, Number> scatterChart;

    // Attributs listes
    private List<String> regionsByMostAffected;
    private List<String> regionsEpicentrales;


    public void initialize() {
        // Crée le change listener pour mettre à jour les graphiques
        initListeSeismeListener();
        // Event retour à la page d'accueil
        btnHome.setOnAction(actionEvent -> {
            try {
                MainControler.showHomePage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // Met en place les graphiques
        setUpAllGraphics();
    }

    /**
     * Crée et lie le change listener sur l'attribut statique listeSeismesTrie de MainControler.
     */
    private void initListeSeismeListener(){
        ListChangeListener seismeListener = change -> {
            change.next();
            setUpAllGraphics();
        };
        MainControler.getListeSeismesTries().addListener(seismeListener);
    }

    private void setUpAllGraphics(){
        // liste pour stocker les régions
        regionsEpicentrales = MainControler.getListeSeismesTries().stream()
                .map(Seisme::getRegion)
                .distinct()
                .collect(Collectors.toList());

        //regions les plus affectées dans l'ordre (list pour le stacked)
        regionsByMostAffected = MainControler.getListeSeismesTries().stream()
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
        //PIE 1 (régions)
        setUpFirstPie();
        //PIE 2 (qualité epicentrale)
        setUpSecondPie();
        // ScatterChart
        setUpScatterChart();
        // Graphique du nombre de séismes dans des intervalles d'intensité
        setUpBarChartIntervalles();

    }

    /**
     * Configure tous les labels de l'onglet des données statistiques (Onglet 1 de l'Overview)
     */
    private void setUpDatasTab() {

        // Seisme Totaux
        seismeTotaux.setText(String.valueOf(MainControler.getListeSeismesTries().size()));

        // Region la plus affectée
        regionPlusAffecte.setText(regionsByMostAffected.get(0));

        // Region la moins affectée
        List<String> regionsByLeastAffected = new ArrayList<>(regionsByMostAffected);
        Collections.reverse(regionsByLeastAffected);
        regionMoinsAffecte.setText(regionsByLeastAffected.get(0));


        // statistiques sur l'intensité
        DoubleSummaryStatistics statsIntensite = MainControler.getListeSeismesTries().stream()
                .mapToDouble(Seisme::getIntensite).summaryStatistics();

        double maxIntensite = statsIntensite.getMax();

        // Date et region ayant subi intensité
        List<Seisme> seismesMaxIntensite = MainControler.getListeSeismesTries().stream()
                .filter(seisme -> seisme.getIntensite() == maxIntensite)
                .collect(Collectors.toList());

        String result;
        if (seismesMaxIntensite.isEmpty()) {
            result = "Aucun séisme enregistré";
        }
        else {
            result = seismesMaxIntensite.stream()
                    .map(seisme -> "Date : " + seisme.dateProperty().get() + ", Région : " + seisme.getRegion())
                    .collect(Collectors.joining("\n"));
        }
        seismePlusIntense.setText(result);


        // Nombre de seisme a intensité supérieure a 5
        long nombreSeismesSup5 = MainControler.getListeSeismesTries().stream()
                .filter(seisme -> seisme.getIntensite() > 5)
                .count();
        totalSeismeSuperieurA5.setText(Long.toString(nombreSeismesSup5));

        // Liste des intensités des séismes (pour le code)
        List<Double> intensites = MainControler.getListeSeismesTries().stream()
                .map(Seisme::getIntensite)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // intensité min
        double minIntensite = statsIntensite.getMin();
        IntensiteMin.setText("Minimum : " + minIntensite);

        // Intensité moyenne
        // Calculer la moyenne de l'intensité totale
        double moyenneIntensite = statsIntensite.getAverage();
        IntensiteMoy.setText(", Moyenne  : " + moyenneIntensite);

        //Intensité max
        IntensiteMax.setText(", Maximum : " + maxIntensite);


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
                .mapToDouble(i -> Math.pow(i - statsIntensite.getAverage(), 2))
                .sum();
        double ecartType = Math.sqrt(sommeCarres / intensites.size());
        IntensiteEcartype.setText(", Ecart - type : " + ecartType);


        // étendue de l'intensité
        double etendue = statsIntensite.getMax() - statsIntensite.getMin();
        IntensiteEtendu.setText(", Etendue " + etendue);


        // Nombre de regions
        regionTotaux.setText(Integer.toString(regionsEpicentrales.size()));

    }


    /**
     * Configure et remplit le graphique en barre de l'Overview.
     */
    private void setUpBarChart(){

        XYChart.Series<String, Number> mostAffectedSeries1 = new XYChart.Series<>();
        mostAffectedSeries1.setName("Minimum");
        XYChart.Series<String, Number> mostAffectedSeries2 = new XYChart.Series<>();
        mostAffectedSeries2.setName("Moyenne");
        XYChart.Series<String, Number> mostAffectedSeries3 = new XYChart.Series<>();
        mostAffectedSeries3.setName("Maximum");

        for (String region : regionsByMostAffected) {
            List<Double> intensiteValues = MainControler.getListeSeismesTries().stream()
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

        mostAffectedChart.getData().setAll(mostAffectedSeries1, mostAffectedSeries2, mostAffectedSeries3);
        mostAffectedChart.setTitle("Minimum, moyenne et maximum de l'intensité des régions les plus touchées");




    }

    /**
     * Rempli le premier graphique en camenbert de l'Overview
     */
    private void setUpFirstPie(){
        // Créer une liste pour stocker le nombre de séismes par région
        List<Long> nombreSeismesParRegion = regionsEpicentrales.stream()
                .map(region -> MainControler.getListeSeismesTries().stream()
                        .filter(seisme -> seisme.getRegion().equals(region))
                        .count())
                .collect(Collectors.toList());

        // Définir un seuil pour la catégorie "autres" des régions moins fréquentes
        long seuil = 80; // Seuil

        // Créer une liste pour stocker les données du pie
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // Parcourir les régions et le nombre de séismes correspondant
        for (int i = 0; i < regionsEpicentrales.size(); ++i) {
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
            PieRegion.setData(pieChartData);
            PieRegion.setTitle("Répartiton des séismes en fonction des régions");

        }
    }

    /**
     * Rempli le deuxième graphique en camenbert de l'Overview
     */
    private void setUpSecondPie(){
        //liste pour stocker la qualité épicentrale des séismes
        List<String> qualitesEpicentrales = MainControler.getListeSeismesTries().stream()
                .map(Seisme::getQualiteIntensiteEpicentre)
                .distinct()
                .collect(Collectors.toList());

        // liste pour stocker le nombre de seisme par qualité
        List<Long> nombreSeismesParQualite = qualitesEpicentrales.stream()
                .map(qualite -> MainControler.getListeSeismesTries().stream()
                        .filter(seisme -> seisme.getQualiteIntensiteEpicentre().equals(qualite))
                        .count())
                .collect(Collectors.toList());

        // liste pour stocker les donnee du pie
        ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();

        long totalSeismes = nombreSeismesParQualite.stream().mapToLong(Long::longValue).sum();

        // Parcourir les qualités épicentrales et le nombre de séismes correspondant
        for (int i = 0; i < qualitesEpicentrales.size(); ++i) {
            String qualite = qualitesEpicentrales.get(i);
            long nombreSeismes = nombreSeismesParQualite.get(i);

            double pourcentage = (nombreSeismes / (double) totalSeismes) * 100;

            // Ajouter les donnees au pie chart
            PieChart.Data data = new PieChart.Data(qualite + " (" + String.format("%.1f", pourcentage) + "%)", nombreSeismes);
            pieChartData2.add(data);

        }

        // Définir les données du pie chart
        PieQualite.setData(pieChartData2);
        PieQualite.setTitle("Répartition des qualités de l'intensité épicentrale");

    }

    /**
     * Rempli le graphique en barre du nombre de séismes en fonction de la plage d'intensité.
     */
    private void setUpBarChartIntervalles(){
        List<String> intensityIntervals = Arrays.asList("0-1", "1-2", "2-3", "3-4", "4-5", "5-6", "6-7", "7-8", "8-9", "9+");

        List<Integer> seismesParIntensite = new ArrayList<>(Collections.nCopies(intensityIntervals.size(), 0));

        for (Seisme seisme : MainControler.getListeSeismesTries()) {
            double intensite = seisme.getIntensite();
            if (intensite != Seisme.DEFAULT_INVALID_INTENSITE) {
                for (int i = 0; i < intensityIntervals.size() - 1; ++i) {
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
        for (int i = 0; i < intensityIntervals.size(); ++i) {
            String interval = intensityIntervals.get(i);
            int count = seismesParIntensite.get(i);
            series.getData().add(new XYChart.Data<>(interval, count));
        }

        intensityChart.getData().setAll(series);

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

        Integer[] bornesAnnee = latestYearSeisme(MainControler.getListeSeismesTries());
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

        for (Seisme seisme : MainControler.getListeSeismesTries()) {
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
    private Integer[] latestYearSeisme(ObservableList<Seisme> listeSeisme){
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

