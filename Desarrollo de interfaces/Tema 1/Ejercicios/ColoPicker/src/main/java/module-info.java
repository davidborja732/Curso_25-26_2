module org.iesch.colopicker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.colopicker to javafx.fxml;
    exports org.iesch.colopicker;
}