module org.iesch.selector_color {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.selector_color to javafx.fxml;
    exports org.iesch.selector_color;
}