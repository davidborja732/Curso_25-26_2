module org.iesch.ejercicio_6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.ejercicio_6 to javafx.fxml;
    exports org.iesch.ejercicio_6;
}