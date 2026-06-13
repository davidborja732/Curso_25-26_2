package org.iesch.datepicker;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HelloController {
    @FXML
    private DatePicker fecha;
    @FXML
    private TextField fechausu;

    @FXML
    public String formateador(DatePicker datePicker){
        DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        return datePicker.getValue().format(formateador);
    }
    @FXML
    public void escribirfecha(){
        fechausu.setText(formateador(fecha));
    }
}