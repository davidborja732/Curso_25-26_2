package org.iesch.explicacion_29_septiembre;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloController {
    @FXML
    private TextField nombre;
    @FXML
    private DatePicker fecha;
    @FXML
    private Spinner<Integer> hora;
    @FXML
    private Spinner<Integer> minutos;
    @FXML
    private TextArea escribircita;
    @FXML
    private Button boton;
    @FXML
    public String formateadorhora(Spinner<Integer> hora,Spinner<Integer> minuto){
        DecimalFormat formatohora=new DecimalFormat("00");
        DecimalFormat formatominuto=new DecimalFormat("00");
        return formatohora.format(hora.getValue())+":"+formatominuto.format(minuto.getValue());
    }
    @FXML
    public String formateadorfecha(DatePicker datePicker){
        DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return datePicker.getValue().format(formateador);
    }
    @FXML
    protected void initialize() {
        fecha.setValue(LocalDate.now());
        SpinnerValueFactory<Integer> factoryHoras=new SpinnerValueFactory.IntegerSpinnerValueFactory(8,17,8);
        SpinnerValueFactory<Integer> factoryMinutos=new SpinnerValueFactory.IntegerSpinnerValueFactory(0,59,0);
        hora.setValueFactory(factoryHoras);
        minutos.setValueFactory(factoryMinutos);
    }
    public void escribir(){//Se hace usando appendText no lo cambio por orgullo

        escribircita.setText(
                "Nombre de la paciente "+nombre.getText()+"\n"+
                "Fecha de la cita "+formateadorfecha(fecha)+"\n"+
                "Hora de la cita es "+formateadorhora(hora,minutos)
                //"Hora de la cita es "+hora.getValue()+":"+minutos.getValue()
        );
    }
}