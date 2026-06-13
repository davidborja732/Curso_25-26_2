module org.iesch.ejercicio4nuevo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.ejercicio4nuevo to javafx.fxml;
    exports org.iesch.ejercicio4nuevo;
}