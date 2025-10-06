package org.iesch.tapbane;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private CheckBox coche;
    @FXML
    private CheckBox monovolumen;
    @FXML
    private Tab cochepestaña;
    @FXML
    private Tab monovolumenpestaña;
    @FXML
    public void initialize(){
        cochepestaña.setDisable(true);
        monovolumenpestaña.setDisable(true);
    }
    @FXML
    public void desabilitarvehi(){
        if (coche.isSelected()){
            cochepestaña.setDisable(false);
        }else {
            cochepestaña.setDisable(true);
        }
    }
    @FXML
    public void desabilitarmono(){
        if (monovolumen.isSelected()){
            monovolumenpestaña.setDisable(false);
        }else {
            monovolumenpestaña.setDisable(true);
        }
    }
}