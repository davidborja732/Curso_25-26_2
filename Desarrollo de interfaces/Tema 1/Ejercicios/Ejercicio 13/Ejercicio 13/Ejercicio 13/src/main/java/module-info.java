module org.iesch.ejercicio_13 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens org.iesch.ejercicio_13 to javafx.fxml;
    exports org.iesch.ejercicio_13;
}