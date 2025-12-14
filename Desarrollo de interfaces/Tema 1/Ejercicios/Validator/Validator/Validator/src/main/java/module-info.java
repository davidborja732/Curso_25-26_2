module org.iesch.validator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;

    opens org.iesch.validator to javafx.fxml;
    exports org.iesch.validator;
}