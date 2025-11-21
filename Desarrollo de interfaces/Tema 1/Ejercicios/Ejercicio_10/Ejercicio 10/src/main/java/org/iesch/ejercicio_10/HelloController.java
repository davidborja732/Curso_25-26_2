package org.iesch.ejercicio_10;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.util.Arrays;
import java.util.List;

public class HelloController {
    public File creararchivo(String archivo){
       return new File("Audios/"+archivo);
    }
    @FXML
    private ComboBox<String> nombres;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    public void initialize(){
        final String[] audiofile = new String[1];
        File carpeta=new File("Audios");
        File[] archivos=carpeta.listFiles();
        assert archivos != null;
        for (File archivocarpeta : Arrays.stream(archivos).toList()) {
            nombres.getItems().add(archivocarpeta.toString());
        }
        nombres.setOnAction(event -> {
            audiofile[0] =new File(nombres.getValue()).toURI().toString();
            Media media = new Media(audiofile[0]);
            MediaPlayer mediaPlayer=new MediaPlayer(media);
            mediaPlayer.stop();
            mediaPlayer.play();
            boton1.setOnAction(event1 -> mediaPlayer.play());
            boton2.setOnAction(event1 -> mediaPlayer.pause());
            boton3.setOnAction(event1-> mediaPlayer.stop());
        });
    }
}