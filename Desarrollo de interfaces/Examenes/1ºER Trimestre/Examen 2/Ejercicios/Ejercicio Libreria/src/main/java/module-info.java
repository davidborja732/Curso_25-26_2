module org.iesch.ej_11 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;
    requires java.xml.crypto;
    requires java.desktop;

    opens org.iesch.ej_11 to javafx.fxml;
    exports org.iesch.ej_11;
}