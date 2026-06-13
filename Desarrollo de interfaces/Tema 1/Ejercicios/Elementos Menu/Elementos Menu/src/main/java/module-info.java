module org.iesch.elementos_menu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.iesch.elementos_menu to javafx.fxml;
    exports org.iesch.elementos_menu;
}