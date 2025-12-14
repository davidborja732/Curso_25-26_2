module org.iesch.explicacion_29_septiembre {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.explicacion_29_septiembre to javafx.fxml;
    exports org.iesch.explicacion_29_septiembre;
}