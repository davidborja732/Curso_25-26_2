module org.iesch.evaluacion_inicial {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.evaluacion_inicial to javafx.fxml;
    exports org.iesch.evaluacion_inicial;
}