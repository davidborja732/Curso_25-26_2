module org.iesch.combobox {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.combobox to javafx.fxml;
    exports org.iesch.combobox;
}