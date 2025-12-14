module org.iesch.boton_imagenes {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.boton_imagenes to javafx.fxml;
    exports org.iesch.boton_imagenes;
}