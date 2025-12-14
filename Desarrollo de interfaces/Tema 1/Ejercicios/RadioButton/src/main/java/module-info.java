module org.iesch.radiobutton {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.radiobutton to javafx.fxml;
    exports org.iesch.radiobutton;
}