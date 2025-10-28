module org.iesch.botonfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.iesch.botonfx to javafx.fxml;
    exports org.iesch.botonfx;
}