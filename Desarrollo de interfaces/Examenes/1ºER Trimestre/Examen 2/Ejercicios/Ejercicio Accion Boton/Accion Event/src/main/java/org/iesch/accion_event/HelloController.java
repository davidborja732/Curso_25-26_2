package org.iesch.accion_event;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button boton1 ;
    @FXML
    private Button boton2 ;

    @FXML
    protected void initialize() {
        welcomeText.setText("");
        boton1.setOnAction(actionEvent -> welcomeText.setText("Hola"));
        boton2.setOnAction(actionEvent -> welcomeText.setText("Adios"));

    }
}