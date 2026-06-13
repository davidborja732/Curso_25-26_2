module org.iesch.ejercicio_14 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens org.iesch.ejercicio_14 to javafx.fxml;
    exports org.iesch.ejercicio_14;
}