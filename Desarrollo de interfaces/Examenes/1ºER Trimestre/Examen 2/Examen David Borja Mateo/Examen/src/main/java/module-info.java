module org.iesch.examen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens org.iesch.examen to javafx.fxml;
    exports org.iesch.examen;
}