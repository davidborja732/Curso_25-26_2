package org.example.prueba2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button boton1;
    @FXML
    protected void A() {
        welcomeText.setText("A");
    }
    @FXML
    protected void B() {
        welcomeText.setText("B");
    }
    @FXML
    protected void C() {
        welcomeText.setText("C");
    }
    @FXML
    protected void D() {
        welcomeText.setText("D");
    }
    @FXML
    protected void E() {
        welcomeText.setText("E");
    }
    @FXML
    protected void F() {
        welcomeText.setText("F");
    }
    @FXML
    protected void prueba() {
        welcomeText.setText("prueba");
    }


}