package org.iesch.ejercicio4nuevo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private ImageView foto;
    @FXML
    private Slider slider;

    @FXML
    protected void initialize() {
        slider.setMax(foto.getFitHeight());
    }
    @FXML
    protected void aumentadismi() {
        foto.setFitHeight(slider.getValue());
    }
}