package org.iesch.pruebajavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField cajarespuesta;
    @FXML
    private TextField cajausuario;
    @FXML
    private Button botonsaludar;
    @FXML
    protected void Botonsaluda() {
        cajarespuesta.setText("Hola "+cajausuario.getText());
    }
}