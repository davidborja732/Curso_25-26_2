package org.iesch.ejercicio_6;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class HelloController {
    @FXML
    private Pane panel;
    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuItem menu1;
    @FXML
    private MenuItem menu2;
    @FXML
    private MenuItem menu3;
    @FXML
    public void boton1Action(){
        boton4Action();
        panel.getStylesheets().add(getClass().getResource("Estilo1.css").toExternalForm());
    }
    @FXML
    public void boton2Action(){
        boton4Action();
        panel.getStylesheets().add(getClass().getResource("Estilo2.css").toExternalForm());
    }
    @FXML
    public void boton4Action(){
        panel.getStylesheets().clear();
    }


}