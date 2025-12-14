module org.iesch.color_checker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.color_checker to javafx.fxml;
    exports org.iesch.color_checker;
}