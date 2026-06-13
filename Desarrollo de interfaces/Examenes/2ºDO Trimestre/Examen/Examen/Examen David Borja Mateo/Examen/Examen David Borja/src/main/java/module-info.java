module org.iesch.examen_david_borja {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.iesch.examen_david_borja to javafx.fxml;
    exports org.iesch.examen_david_borja;
}