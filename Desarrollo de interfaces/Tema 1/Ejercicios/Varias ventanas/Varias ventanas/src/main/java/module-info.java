module org.iesch.varias_ventanas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.iesch.varias_ventanas to javafx.fxml;
    exports org.iesch.varias_ventanas;
}