package org.iesch.ejercicio_1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class HelloController {
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private Pane pane;
    @FXML
    private CheckBox checkBox;
    @FXML
    private RadioButton radioButton;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Slider slider;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    public void initialize(){
        List<File> estilos= List.of(new File("E:\\Ejercicios\\CSS\\Ejercicio 1\\Ejercicio 1\\src\\main\\resources\\org\\iesch\\ejercicio_1").listFiles());
        //List<File> estilos= List.of(new File("src/main/resources/org/iesch/ejercicio_1").listFiles());
        System.out.println(estilos.size());
        estilos.forEach(file ->
                {
                    if (file.getName().endsWith(".css")) {
                        comboBox.getItems().add(file.getName());
                    }
                }
        );
        progressBar.setProgress(0.25);
        progressIndicator.setProgress(0.35);
    }
    public void comboboxaccion(){
        System.out.println(comboBox.getValue());
        cargarestilo(comboBox.getValue());
        barras();
    }
    public void cargarestilo(String estilousar){
        pane.getStylesheets().clear();
        pane.getStylesheets().add(getClass().getResource(estilousar).toExternalForm());
        progressBar.setProgress(0.25);
        progressIndicator.setProgress(0.35);
    }
    public void barras(){
        for (double i=0.1;i<=0.5;i=i+0.1){
            progressBar.setProgress(i);
            progressIndicator.setProgress(i);
        }
        progressBar.setProgress(0);
        progressIndicator.setProgress(0);
    }


}