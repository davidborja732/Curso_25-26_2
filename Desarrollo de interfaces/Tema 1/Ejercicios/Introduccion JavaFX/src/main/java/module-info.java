module org.example.prueba2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.prueba2 to javafx.fxml;
    exports org.example.prueba2;
}