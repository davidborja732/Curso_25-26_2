module org.iesch.pruebajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.pruebajavafx to javafx.fxml;
    exports org.iesch.pruebajavafx;
}