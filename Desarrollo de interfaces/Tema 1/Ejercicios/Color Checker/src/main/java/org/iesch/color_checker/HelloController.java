package org.iesch.color_checker;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelloController {
    @FXML
    private ColorPicker color1;
    @FXML
    private ColorPicker color2;
    @FXML
    private ComboBox<Integer> tamanocampo;
    @FXML
    private TextArea textoamanipular;

    @FXML
    protected void initialize() {
        for (int i=1;i<51;i++){
            tamanocampo.getItems().addAll(i);
        }
    }
    public void cambiartamano(){
    }
    public void cambiarfondo(){
        textoamanipular.setStyle("-fx-background-color: #"+color1.getValue()+";");
    }
    public void cambiartexto(){

    }
}