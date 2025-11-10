module org.iesch.accion_event {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens org.iesch.accion_event to javafx.fxml;
    exports org.iesch.accion_event;
}