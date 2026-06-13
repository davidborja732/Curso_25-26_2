package org.iesch.ejercicio_3;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HelloController {
    int suma;
    Random random=new Random();
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
    private Button boton6;
    @FXML
    private Button boton7;
    @FXML
    private Button boton8;
    @FXML
    private Button boton9;
    @FXML
    private Button boton10;
    @FXML
    private Button boton11;
    @FXML
    private Button boton12;
    @FXML
    private Button botonnuevojuego;
    @FXML
    private Button botonInstrucciones;
    @FXML
    private TextField numeromaquina;
    @FXML
    private TextField numerousuario;
    @FXML
    public void initialize() {
        numeromaquina.setEditable(false);
        numeromaquina.setText(String.valueOf(random.nextInt(200) + 1));
    }
    @FXML
    public void setBoton1(){
        esigual();
        sumar(Integer.valueOf(boton1.getText()),boton1);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton2(){
        esigual();
        sumar(Integer.valueOf(boton2.getText()),boton2);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton3(){
        esigual();
        sumar(Integer.valueOf(boton3.getText()),boton3);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton4(){
        esigual();
        sumar(Integer.valueOf(boton4.getText()),boton4);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton5(){
        esigual();
        sumar(Integer.valueOf(boton5.getText()),boton5);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton6(){
        esigual();
        sumar(Integer.valueOf(boton6.getText()),boton6);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton7(){
        esigual();
        sumar(Integer.valueOf(boton7.getText()),boton7);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton8(){
        esigual();
        sumar(Integer.valueOf(boton8.getText()),boton8);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton9(){
        esigual();
        sumar(Integer.valueOf(boton9.getText()),boton9);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton10(){
        esigual();
        sumar(Integer.valueOf(boton10.getText()),boton10);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton11(){
        esigual();
        sumar(Integer.valueOf(boton11.getText()),boton11);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }
    @FXML
    public void setBoton12(){
        esigual();
        sumar(Integer.valueOf(boton12.getText()),boton12);
        if (esigual()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Premio");
            alert.setContentText("Has acertado");
            alert.show();
            setBotonnuevojuego();
        }
    }

    public void sumar(int valor,Button botonparaemtro){
        suma+=valor;
        numerousuario.setText(String.valueOf(suma));
        botonparaemtro.setDisable(true);

    }
    public boolean esigual(){
        if (numeromaquina.getText().equals(numerousuario.getText())){
            return true;
        }else {
            return false;
        }
    }
    @FXML
    public void setBotonnuevojuego(){
        numeromaquina.setText(String.valueOf(random.nextInt(200) + 1));
        numeromaquina.setEditable(false);
        boton1.setDisable(false);
        boton2.setDisable(false);
        boton3.setDisable(false);
        boton4.setDisable(false);
        boton5.setDisable(false);
        boton6.setDisable(false);
        boton7.setDisable(false);
        boton8.setDisable(false);
        boton9.setDisable(false);
        boton10.setDisable(false);
        boton11.setDisable(false);
        boton12.setDisable(false);
        suma=0;
        numerousuario.clear();
    }



}