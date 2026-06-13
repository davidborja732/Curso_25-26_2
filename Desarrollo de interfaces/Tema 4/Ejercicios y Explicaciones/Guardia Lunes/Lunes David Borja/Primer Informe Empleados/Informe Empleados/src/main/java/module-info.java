module org.iesch.informe_empleados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // JasperReports 7.x
    requires net.sf.jasperreports.core;

    // Abrir tu paquete a JavaFX y JasperReports
    opens org.iesch.informe_empleados to javafx.fxml, net.sf.jasperreports.core;

    exports org.iesch.informe_empleados;
}
