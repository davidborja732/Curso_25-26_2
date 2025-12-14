package org.iesch.validator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class HelloController {
    @FXML
    CheckBox check;
    @FXML
    ComboBox<Integer> choice;
    @FXML
    TextField texto;
    @FXML
    Button boton;
    @FXML
    ValidationSupport v = new ValidationSupport();
    @FXML
    public void initialize(){
        for (int i = 0; i < 10; i++) {
            choice.getItems().add(i);
        }
        v.registerValidator(check, (Control c, Boolean newValue)
                -> ValidationResult.fromErrorIf(c,"CheckBox pulsado",!newValue));
        v.registerValidator(choice, Validator.createEmptyValidator("Numero requerido"));
        v.registerValidator(texto, Validator.createEmptyValidator("Texto requerido"));
    }
    @FXML
    public void botonaccion(){
        /*Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.show();*/
        if (v.isInvalid()){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LLena todos los datos");
            alert.show();
        }else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Todo ok continua");
            alert.show();
        }
    }

}