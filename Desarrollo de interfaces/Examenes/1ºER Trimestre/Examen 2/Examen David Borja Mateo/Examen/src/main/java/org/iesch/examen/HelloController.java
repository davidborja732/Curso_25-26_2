package org.iesch.examen;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class HelloController {
    @FXML
    private MenuBar menu;
    @FXML
    private Menu menuestilos;
    @FXML
    private Menu menuayu;
    @FXML
    private MenuItem menuestilo1;
    @FXML
    private MenuItem menuestiloborrar;
    @FXML
    private MenuItem menuestilo2;
    @FXML
    private MenuItem menuayuda;

    @FXML
    private Pane panel;
    @FXML
    private ToggleButton botonF;
    @FXML
    private ToggleButton botonAM;
    @FXML
    private ToggleButton botonPV;
    @FXML
    private ToggleButton botonN;
    @FXML
    private RadioButton radioF;
    @FXML
    private RadioButton radioAM;
    @FXML
    private RadioButton radioPV;
    @FXML
    private RadioButton radioN;
    @FXML
    private Slider slider;
    @FXML
    private ImageView independenciaimagen;
    @FXML
    private ProgressBar atencionBarra;
    @FXML
    private ProgressIndicator atencionHabla;

    @FXML
    public void setBotonF(){
        atencionBarra.setProgress(1);
    }
    @FXML
    public void setBotonAM(){
        atencionBarra.setProgress(0.75);
    }
    @FXML
    public void setBotonPV(){
        atencionBarra.setProgress(0.5);
    }
    @FXML
    public void setBotonN(){
        atencionBarra.setProgress(0.25);
    }
    @FXML
    public void setRadioF(){
        atencionHabla.setProgress(1);
    }
    @FXML
    public void setRadioAM(){
        atencionHabla.setProgress(0.75);
    }
    @FXML
    public void setRadioPV(){
        atencionHabla.setProgress(0.5);
    }
    @FXML
    public void setRadioN(){
        atencionHabla.setProgress(0.25);
    }
    @FXML
    public void Setslider(){
        if (slider.getValue()<33){
            independenciaimagen.setImage(new Image(Objects.requireNonNull(getClass().getResource("nivel1.jpg")).toExternalForm()));
        } else if (slider.getValue()<66){
            independenciaimagen.setImage(new Image(Objects.requireNonNull(getClass().getResource("nivel2.jpg")).toExternalForm()));
        } else {
            independenciaimagen.setImage(new Image(Objects.requireNonNull(getClass().getResource("nivel3.jpg")).toExternalForm()));
        }
        System.out.println(slider.getValue());
    }
    @FXML
    public void SetEstilo1(){
        panel.getStylesheets().clear();
        panel.getStylesheets().add(getClass().getResource("Estilo1.css").toExternalForm());
    }
    @FXML
    public void SetEstilo2(){
        panel.getStylesheets().clear();
        panel.getStylesheets().add(getClass().getResource("Estilo2.css").toExternalForm());
    }
    @FXML
    public void SetEstiloDefecto(){
        panel.getStylesheets().clear();
    }
    @FXML
    public void setMenuayu() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("AyudaExamen.chm"));
    }


}