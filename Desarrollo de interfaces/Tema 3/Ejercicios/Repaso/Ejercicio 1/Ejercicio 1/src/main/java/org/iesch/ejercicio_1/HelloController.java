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
        List<File> estilos= List.of(new File("E:\\Grado Superior\\Curso_25-26_2\\Desarrollo de interfaces\\Tema 3\\Ejercicios\\Repaso\\Ejercicio 1\\Ejercicio 1\\src\\main\\resources\\org\\iesch\\ejercicio_1").listFiles());
        System.out.println(estilos.size());
        estilos.forEach(file ->
                {
                    if (file.getName().endsWith(".css")) {
                        comboBox.getItems().add(file.getName());
                    }
                }
        );
    }
    public void comboboxaccion(){
        System.out.println(comboBox.getValue());
        cargarestilo(comboBox.getValue());
        barras();
    }
    public void cargarestilo(String estilousar){
        pane.getStylesheets().clear();
        pane.getStylesheets().add(getClass().getResource(estilousar).toExternalForm());
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