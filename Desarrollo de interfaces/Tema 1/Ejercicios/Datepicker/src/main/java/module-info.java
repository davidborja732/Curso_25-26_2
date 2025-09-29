module org.iesch.datepicker {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.datepicker to javafx.fxml;
    exports org.iesch.datepicker;
}