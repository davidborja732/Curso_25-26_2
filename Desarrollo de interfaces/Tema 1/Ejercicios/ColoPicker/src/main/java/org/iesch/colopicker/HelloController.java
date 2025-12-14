package org.iesch.colopicker;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ColorPicker color1;
    @FXML
    private ColorPicker color2;
    @FXML
    private TextField textocaja;
    @FXML
    private Button boton1;
    @FXML
    private Label label1;

    @FXML
    protected void establecerfondo() {
        boton1.setBackground(new Background(new BackgroundFill(color2.getValue(),null,null)));
        textocaja.setBackground(new Background(new BackgroundFill(color2.getValue(),null,null)));
        label1.setBackground(new Background(new BackgroundFill(color2.getValue(),null,null)));
    }
    @FXML
    protected void establecercolortexto() {
        boton1.setTextFill(color1.getValue());
        textocaja.setStyle("-fx-text-fill: green;");
        label1.setTextFill(color1.getValue());
        System.out.println(color1.getValue());
    }
}