package org.iesch.ejercicio_3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane panel;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button boton5;

    @FXML
    public void boton1Action(){
        panel.getStylesheets().add(getClass().getResource("estilos.css").toExternalForm());
    }
    @FXML
    public void boton2Action(){

    }
    @FXML
    public void boton3Action(){

    }
    @FXML
    public void boton4Action(){
        panel.getStylesheets().add(getClass().getResource("default").toExternalForm());
    }
    @FXML
    public void boton5Action(){

    }

}