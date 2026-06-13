package org.iesch.evaluacion_inicial;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Button saluda;
    @FXML
    private Button nosaludar;
    @FXML
    private Label hola;
    @FXML
    public void initialize(){
        hola.setVisible(false);
    }
    @FXML
    public void saludaaccion(){
        hola.setVisible(true);
    }
    @FXML
    public void nosaludaaccion(){
        hola.setVisible(false);
    }

    /*@FXML
    private TextField pelicula;
    @FXML
    private Button agregar;
    @FXML
    private ComboBox<String> combopelis;

    @FXML
    protected void Agregar_peli() {
        combopelis.getItems().add(pelicula.getText());
        pelicula.clear();
    }*/
}