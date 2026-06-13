package org.iesch.ejercicio_2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    private Label imc;
    @FXML
    private Label estado;
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    public void initialize(){
        Tooltip tool1=new Tooltip("Introduce el peso en kilos");
        Tooltip tool2=new Tooltip("Introduce la altura en metros");
        imc.setTooltip(tool1);
        estado.setTooltip(tool2);
    }
    @FXML
    public void action1Boton(){
        if (texto1.getText().isEmpty() && texto1.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos");
            alert.setContentText("Rellena ambos campos");
            alert.show();
        }else {
            int peso=Integer.parseInt(texto1.getText());
            Double altura= metros(Double.parseDouble(texto2.getText()));
            double operacion=peso/(altura*altura);
            if (operacion<18.5){
                imc.setText(Double.toString(operacion));
                estado.setText("Bajo peso");
            }else if (operacion<25.0){
                imc.setText(Double.toString(operacion));
                estado.setText("Normal");
            }else if (operacion<30.0){
                imc.setText(Double.toString(operacion));
                estado.setText("Sobrepeso");
            }
            else if (operacion<35.0){
                imc.setText(Double.toString(operacion));
                estado.setText("Obesidad 1");
            }
            else if (operacion<40.0){
                imc.setText(Double.toString(operacion));
                estado.setText("Obesidad 2");
            }
            else {
                imc.setText(Double.toString(operacion));
                estado.setText("Obesidad 3");
            }
        }
    }
    @FXML
    public void setButton2(){
        texto1.clear();
        texto2.clear();
        estado.setText("Mete un dato");
        imc.setText("Mete un dato");
    }
    @FXML
    public void setButton3() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("AyudaAplicacionCalculo.chm"));
    }
    public double metros(double cm){
        return cm/100;
    }


}