package org.iesch.barra_progreso;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private TextField Progreso1;
    @FXML
    private TextField Progreso2;
    @FXML
    private ProgressIndicator barra2;
    @FXML
    private Slider barra3;
    @FXML
    private Spinner barra4; ;
    @FXML
    private Button aumentar1;
    @FXML
    private Button decrementar1;
    @FXML
    private Button aumentar2;
    @FXML
    private Button decrementar2;
    @FXML
    private Button aumentar3;
    @FXML
    private Button decrementar3;
    @FXML
    private TextField Progreso3;

    @FXML
    private ProgressBar barra1;

    @FXML
    protected void Aumentarvalor1() {
        if (!(barra1.getProgress() == 1)){
            barra1.setProgress(barra1.getProgress() + 0.20);
            Progreso1.setText(String.valueOf(barra1.getProgress()));
        }


    }
    @FXML
    protected void disminuirvalor1() {
        if (barra1.getProgress() >0){
            barra1.setProgress(barra1.getProgress()-0.20);
            Progreso1.setText(String.valueOf(barra1.getProgress()));
        }
    }
    @FXML
    protected void Aumentarvalor2() {
        if (!(barra2.getProgress() == 1)){
            barra2.setProgress(barra2.getProgress() + 0.20);
            Progreso2.setText(String.valueOf(barra2.getProgress()));
        }


    }
    @FXML
    protected void disminuirvalor2() {
            barra2.setProgress(barra2.getProgress()-0.20);
            Progreso2.setText(String.valueOf(barra2.getProgress()));
    }
    @FXML
    protected void Aumentarvalor3() {
        barra3.setBlockIncrement(20.0);
        if (!(barra3.getValue() == 1)){
            barra3.increment();
            Progreso3.setText(String.valueOf(barra3.getValue()));
        }


    }
    @FXML
    protected void disminuirvalor3() {
        if (barra3.getValue()>0){
            barra3.decrement();
            Progreso3.setText(String.valueOf(barra3.getValue()));
        }
    }
    @FXML
    protected void valor4(){
        Progreso3.setText(String.valueOf(barra3.getValue()));
    }
    @FXML
    protected void initialize(){
        SpinnerValueFactory<Integer> factory=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0,5);
        barra4.setValueFactory(factory);
    }
}