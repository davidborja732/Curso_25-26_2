package org.iesch.almacen_imagenes;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class HelloController {
    @FXML
    private ComboBox<String> imagenes;
    @FXML
    private ImageView imagenesver;


    @FXML
    protected void initialize(){
        imagenes.getItems().addAll("Perro","Gato");
    }
    public void mostrarfoto(){
        Image image;
        if (imagenes.getValue().equals("Perro")){
            imagenesver.setImage(new Image(Objects.requireNonNull(getClass().getResource("Imagenes/perro.jpg")).toExternalForm()));
        } else if (imagenes.getValue().equals("Gato")) {
            imagenesver.setImage(new Image(Objects.requireNonNull(getClass().getResource("Imagenes/gatito.jpg")).toExternalForm()));
        }
    }
}