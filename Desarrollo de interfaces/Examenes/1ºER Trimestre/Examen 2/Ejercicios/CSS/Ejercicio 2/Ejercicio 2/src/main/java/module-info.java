module org.iesch.ejercicio_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens org.iesch.ejercicio_2 to javafx.fxml;
    exports org.iesch.ejercicio_2;
}