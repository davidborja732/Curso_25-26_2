module org.iesch.ejercicio_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens org.iesch.ejercicio_1 to javafx.fxml;
    exports org.iesch.ejercicio_1;
}