package org.iesch.selector_color;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private ComboBox<Integer> rojo;
    @FXML
    private ComboBox<Integer> verde;
    @FXML
    private ComboBox<Integer> azul;
    @FXML
    private TextField texto;

    @FXML
    protected void initialize() {
        for (int i = 0; i < 256; i++) {
            rojo.getItems().add(i);
            verde.getItems().add(i);
            azul.getItems().add(i);
        }
        rojo.getSelectionModel().select(0);
        verde.getSelectionModel().select(0);
        azul.getSelectionModel().select(0);
    }
    @FXML
    public void cambiocolor(){
        Color color=Color.rgb(rojo.getValue(), verde.getValue(), azul.getValue());
        texto.setBackground(new Background(new BackgroundFill(color,null,null)));
    }
}