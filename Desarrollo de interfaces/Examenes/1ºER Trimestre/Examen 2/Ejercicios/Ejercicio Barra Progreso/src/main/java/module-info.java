module org.iesch.barra_progreso {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.barra_progreso to javafx.fxml;
    exports org.iesch.barra_progreso;
}