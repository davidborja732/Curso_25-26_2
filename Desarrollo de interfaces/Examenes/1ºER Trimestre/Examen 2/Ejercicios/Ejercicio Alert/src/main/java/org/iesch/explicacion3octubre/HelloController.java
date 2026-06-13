package org.iesch.explicacion3octubre;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;

    @FXML
    private void mensaje1(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esto es el título");
        alert.setHeaderText("Esto es el encabezado");
        alert.setContentText("Esto es el contenido");
        alert.show();
    }
    @FXML
    private void mensaje2(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esto es el título");
        alert.setContentText("Esto es el contenido");
        alert.setHeaderText(null);
        alert.show();
    }
    @FXML
    private void mensaje3(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Esto es el título");
        alert.setHeaderText("Esto es el encabezado");
        alert.setContentText("Esto es el contenido");
        alert.show();
    }
    @FXML
    private void mensaje4(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Esto es el título");
        alert.setHeaderText("Esto es el encabezado");
        alert.setContentText("Esto es el contenido");
        alert.show();
    }
}