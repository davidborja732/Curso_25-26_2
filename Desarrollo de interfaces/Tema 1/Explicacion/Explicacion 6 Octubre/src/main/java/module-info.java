module org.iesch.explicacion_6_octubre {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.explicacion_6_octubre to javafx.fxml;
    exports org.iesch.explicacion_6_octubre;
}