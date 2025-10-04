module org.iesch.ejercicio_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.ejercicio_2 to javafx.fxml;
    exports org.iesch.ejercicio_2;
}