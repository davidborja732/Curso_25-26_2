module org.iesch.almacen_imagenes {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.almacen_imagenes to javafx.fxml;
    exports org.iesch.almacen_imagenes;
}