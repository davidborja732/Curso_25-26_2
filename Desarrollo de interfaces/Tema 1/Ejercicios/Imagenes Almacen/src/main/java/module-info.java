module org.iesch.imagenes_almacen {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.imagenes_almacen to javafx.fxml;
    exports org.iesch.imagenes_almacen;
}