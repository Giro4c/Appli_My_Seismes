module fr.amu.iut.prototype1.appli_my_seismes {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
    opens fr.amu.iut.prototype1.appli_my_seismes to javafx.fxml;
    exports fr.amu.iut.prototype1.appli_my_seismes;
}