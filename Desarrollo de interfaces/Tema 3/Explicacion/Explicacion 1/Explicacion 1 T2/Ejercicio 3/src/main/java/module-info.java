module org.iesch.ejercicio_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.ejercicio_3 to javafx.fxml;
    exports org.iesch.ejercicio_3;
}