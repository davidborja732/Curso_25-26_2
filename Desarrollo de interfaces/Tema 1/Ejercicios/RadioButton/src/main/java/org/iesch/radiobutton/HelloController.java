package org.iesch.radiobutton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private RadioButton zaragoza;
    @FXML
    private RadioButton huesca;
    @FXML
    private RadioButton teruel;
    @FXML
    private TextField texto;
    @FXML
    protected void textoset() {
        if (zaragoza.isArmed()){
            texto.setText("Zaragoza");
        } else if (teruel.isArmed()) {
            texto.setText("Teruel");
        } else if (huesca.isArmed()){
            texto.setText("Huesca");
        }
    }
}