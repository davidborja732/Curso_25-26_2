package org.iesch.ejercicio_2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Button sumar;
    @FXML
    private Button restar;
    @FXML
    private Button multiplicar;
    @FXML
    private TextField n1;
    @FXML
    private TextField n2;
    @FXML
    private TextField resultado;


    @FXML
    protected void sumar() {
        resultado.clear();
        int resultadooperacion=Integer.parseInt(n1.getText())+Integer.parseInt(n2.getText());
        resultado.setText(String.valueOf(resultadooperacion));
    }
    @FXML
    protected void restar() {
        resultado.clear();
        int resultadooperacion=Integer.parseInt(n1.getText())-Integer.parseInt(n2.getText());
        resultado.setText(String.valueOf(resultadooperacion));
    }
    @FXML
    protected void multiplicar() {
        resultado.clear();
        int resultadooperacion=Integer.parseInt(n1.getText())*Integer.parseInt(n2.getText());
        resultado.setText(String.valueOf(resultadooperacion));
    }
}