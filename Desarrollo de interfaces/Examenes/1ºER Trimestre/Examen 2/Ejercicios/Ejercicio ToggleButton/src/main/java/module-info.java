module org.iesch.togglebutton {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.togglebutton to javafx.fxml;
    exports org.iesch.togglebutton;
}