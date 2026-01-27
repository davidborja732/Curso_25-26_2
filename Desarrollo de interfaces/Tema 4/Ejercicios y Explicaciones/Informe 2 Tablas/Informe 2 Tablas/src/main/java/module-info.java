module org.iesch.informe_2_tablas {
    requires javafx.controls;
    requires javafx.fxml;
    requires net.sf.jasperreports.core;
    requires java.sql;


    opens org.iesch.informe_2_tablas to javafx.fxml;
    exports org.iesch.informe_2_tablas;
}