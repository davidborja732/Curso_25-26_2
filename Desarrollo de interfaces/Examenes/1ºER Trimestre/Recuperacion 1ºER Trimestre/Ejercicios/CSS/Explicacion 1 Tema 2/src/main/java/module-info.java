module org.iesch.explicacion_1_tema_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.explicacion_1_tema_2 to javafx.fxml;
    exports org.iesch.explicacion_1_tema_2;
}