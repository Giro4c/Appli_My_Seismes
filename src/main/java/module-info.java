module fr.amu.iut.prototype1.appli_my_seismes {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
    requires com.gluonhq.maps;

    opens fr.amu.iut.prototype1.appli_my_seismes to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes;
//    exports fr.amu.iut.prototype1.appli_my_seismes.functionalities;
//    opens fr.amu.iut.prototype1.appli_my_seismes.functionalities to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes.datas;
    opens fr.amu.iut.prototype1.appli_my_seismes.datas to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes.utilitaries;
    opens fr.amu.iut.prototype1.appli_my_seismes.utilitaries to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes.datas.filters;
    opens fr.amu.iut.prototype1.appli_my_seismes.datas.filters to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes.functionalities.table_datas;
    opens fr.amu.iut.prototype1.appli_my_seismes.functionalities.table_datas to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes.functionalities.seismic_map;
    opens fr.amu.iut.prototype1.appli_my_seismes.functionalities.seismic_map to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes.functionalities.overview;
    opens fr.amu.iut.prototype1.appli_my_seismes.functionalities.overview to javafx.fxml;
//    exports fr.amu.iut.prototype1.appli_my_seismes;
//    opens fr.amu.iut.prototype1.appli_my_seismes to javafx.fxml;
}