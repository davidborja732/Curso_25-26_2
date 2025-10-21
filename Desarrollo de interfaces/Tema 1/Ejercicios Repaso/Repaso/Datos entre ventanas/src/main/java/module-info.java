module org.iesch.datos_entre_ventanas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.iesch.datos_entre_ventanas to javafx.fxml;
    exports org.iesch.datos_entre_ventanas;
}