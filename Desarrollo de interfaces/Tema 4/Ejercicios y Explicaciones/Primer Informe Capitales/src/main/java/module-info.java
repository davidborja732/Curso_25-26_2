module org.iesch.primer_informe_capitales {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.iesch.primer_informe_capitales to javafx.fxml;
    exports org.iesch.primer_informe_capitales;
}