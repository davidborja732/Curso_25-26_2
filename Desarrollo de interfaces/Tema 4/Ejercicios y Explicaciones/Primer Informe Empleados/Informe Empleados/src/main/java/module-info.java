module org.iesch.informe_empleados {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;
        requires net.sf.jasperreports.core;


        opens org.iesch.informe_empleados to javafx.fxml;
        exports org.iesch.informe_empleados;
        }
