module org.iesch.tapbane {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.tapbane to javafx.fxml;
    exports org.iesch.tapbane;
}