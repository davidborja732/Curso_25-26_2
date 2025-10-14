module org.iesch.datos_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.iesch.datos_1 to javafx.fxml;
    exports org.iesch.datos_1;
}