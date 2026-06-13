package org.iesch.varias_ventanas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloController1 {
    @FXML
    private Button boton1;
    @FXML
    public void cerrar(){
        Stage stage = (Stage) boton1.getScene().getWindow();
        stage.close();
    }

}