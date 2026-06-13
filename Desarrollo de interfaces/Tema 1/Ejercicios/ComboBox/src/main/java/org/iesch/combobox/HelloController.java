package org.iesch.combobox;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private ComboBox<String> instituto;
    @FXML
    private ComboBox<String> provincia;
    @FXML
    private TextField resultado;
    @FXML
    private Button enviarboton;
    @FXML
    protected void initialize() {
        provincia.getItems().addAll("Zaragoza","Huesca","Teruel");

        //resultado.setText(provincia.getValue()+" "+instituto.getValue());
        botonconfirmar();

    }
    @FXML
    protected void institutoadd(){
        instituto.getItems().clear();
        if (provincia.getValue().equals("Zaragoza")){
            instituto.getItems().addAll("Miguel Catalan","Arcosur");
        }
        if (provincia.getValue().equals("Huesca")){
            instituto.getItems().addAll("Piramide","Salesas");
        }
        if (provincia.getValue().equals("Teruel")){
            instituto.getItems().addAll("Segundo de Chomon","Vega del Turia");
        }
    }
    @FXML
    protected  void botonconfirmar(){
        resultado.setText(provincia.getValue()+" "+instituto.getValue());
    }
}