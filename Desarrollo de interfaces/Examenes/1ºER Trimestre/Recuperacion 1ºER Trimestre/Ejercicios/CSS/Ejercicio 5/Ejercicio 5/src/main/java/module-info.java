module org.iesch.ejercicio_5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.ejercicio_5 to javafx.fxml;
    exports org.iesch.ejercicio_5;
}