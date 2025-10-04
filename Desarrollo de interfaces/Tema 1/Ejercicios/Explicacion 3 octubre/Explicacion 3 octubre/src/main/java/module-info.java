module org.iesch.explicacion3octubre {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.explicacion3octubre to javafx.fxml;
    exports org.iesch.explicacion3octubre;
}