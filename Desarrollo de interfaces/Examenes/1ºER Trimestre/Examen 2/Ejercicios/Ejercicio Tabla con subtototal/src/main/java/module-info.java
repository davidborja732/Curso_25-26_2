module org.iesch.ejercicio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens org.iesch.ejercicio to javafx.fxml;
    exports org.iesch.ejercicio;
}