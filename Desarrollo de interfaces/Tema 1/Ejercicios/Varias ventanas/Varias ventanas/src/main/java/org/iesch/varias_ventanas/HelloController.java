package org.iesch.varias_ventanas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private TextField texto3;
    @FXML
    private TextField texto4;
    @FXML
    private TextField texto5;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private CheckBox checkbox1;
    @FXML
    public void abrirayuda(){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ventanadni.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Ayuda DNI");
        stage.setScene(scene);
        stage.show();
    }
    public void abrirayuda2(){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ventancondiciones.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 456, 466);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Aviso Legal y politica de privacidad");
        stage.setScene(scene);
        stage.show();
    }

}