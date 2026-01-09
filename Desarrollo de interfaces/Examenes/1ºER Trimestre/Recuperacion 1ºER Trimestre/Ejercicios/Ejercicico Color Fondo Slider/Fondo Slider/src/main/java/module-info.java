module org.iesch.fondo_slider {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.fondo_slider to javafx.fxml;
    exports org.iesch.fondo_slider;
}