module org.iesch.explicacion_custom {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.iesch.explicacion_custom to javafx.fxml;
    exports org.iesch.explicacion_custom;
}