module org.iesch.repaso_clase {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires net.sf.jasperreports.core;

    opens org.iesch.repaso_clase to javafx.fxml;
    exports org.iesch.repaso_clase;
}