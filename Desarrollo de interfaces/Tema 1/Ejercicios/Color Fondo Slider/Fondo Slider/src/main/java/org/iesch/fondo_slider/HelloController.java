package org.iesch.fondo_slider;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Slider rojo;
    @FXML
    private Slider verde;
    @FXML
    private Slider azul;
    @FXML
    private TextField texto;

    @FXML
    protected void initialize() {
        rojo.setMax(255);
        verde.setMax(255);
        azul.setMax(255);
    }
    @FXML
    public void cambiocolor(){
        Color color=Color.rgb((int) rojo.getValue(), (int) verde.getValue(), (int) azul.getValue());
        texto.setBackground(new Background(new BackgroundFill(color,null,null)));
    }
}