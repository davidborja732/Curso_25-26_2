module org.iesch.ejercicio_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.ejercicio_4 to javafx.fxml;
    exports org.iesch.ejercicio_4;
}