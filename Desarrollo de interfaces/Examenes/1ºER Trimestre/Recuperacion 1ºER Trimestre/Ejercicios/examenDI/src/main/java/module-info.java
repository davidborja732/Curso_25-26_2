module org.iesch.examendi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.sql;

    opens org.iesch.examendi to javafx.fxml;
    exports org.iesch.examendi;
}