module org.iesch.ejercicio_10 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;
    requires javafx.media;

    opens org.iesch.ejercicio_10 to javafx.fxml;
    exports org.iesch.ejercicio_10;
}