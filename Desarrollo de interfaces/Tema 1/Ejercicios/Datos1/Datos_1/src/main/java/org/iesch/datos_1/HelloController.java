package org.iesch.datos_1;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    List<Persona> personas=new ArrayList<>();
    int actualpersona=0;
    ResultSet rs;
    String nombre = "";
    String apellidos= "";
    String localidad= "";
    int salario;
    static String url = "jdbc:mysql://localhost:3306/datos";
    static String user = "root";
    static String clave = "1234";
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private Button nuevore;
    @FXML
    private Button guardar;
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private TextField texto3;
    @FXML
    private TextField texto4;
    @FXML
    public void initialize() {
        Persona persona;
        try {
            Connection con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Nombre,Apellidos,Localidad,Salario from datos.empleados";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                nombre=(rs.getString("Nombre"));
                apellidos=(rs.getString("Apellidos"));
                localidad=(rs.getString("Localidad"));
                salario=(rs.getInt("Salario"));
                personas.add(persona=new Persona(nombre,apellidos,localidad,salario));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Persona personaboton=personas.get(actualpersona);
        texto1.setText(personaboton.nombre);
        texto2.setText(personaboton.apellidos);
        texto3.setText(personaboton.localidad);
        texto4.setText(String.valueOf(personaboton.salario));
    }
    @FXML
    public void setBoton1(){
        if (actualpersona==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver personas");
            alert.setContentText("Ya estas en la primera persona");
            alert.show();
        }else {
            actualpersona=0;
            Persona personaboton=personas.get(actualpersona);
            texto1.setText(personaboton.nombre);
            texto2.setText(personaboton.apellidos);
            texto3.setText(personaboton.localidad);
            texto4.setText(String.valueOf(personaboton.salario));
        }
    }
    public void setBoton2(){
        if (actualpersona==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver personas");
            alert.setContentText("Ya estas en la primera persona");
            alert.show();
        }else {
            actualpersona-=1;
            Persona personaboton=personas.get(actualpersona);
            texto1.setText(personaboton.nombre);
            texto2.setText(personaboton.apellidos);
            texto3.setText(personaboton.localidad);
            texto4.setText(String.valueOf(personaboton.salario));
        }
    }
    public void setBoton3(){
        if (actualpersona==7){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver personas");
            alert.setContentText("Ya estas en la ultima persona");
            alert.show();
        }else {
            actualpersona+=1;
            Persona personaboton=personas.get(actualpersona);
            texto1.setText(personaboton.nombre);
            texto2.setText(personaboton.apellidos);
            texto3.setText(personaboton.localidad);
            texto4.setText(String.valueOf(personaboton.salario));
        }
    }
    public void setBoton4(){
        if (actualpersona==7){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver personas");
            alert.setContentText("Ya estas en la ultima persona");
            alert.show();
        }else {
            actualpersona=7;
            Persona personaboton=personas.get(actualpersona);
            texto1.setText(personaboton.nombre);
            texto2.setText(personaboton.apellidos);
            texto3.setText(personaboton.localidad);
            texto4.setText(String.valueOf(personaboton.salario));
        }
    }
    public void nuevoresgistroaccion(){
        actualpersona=1;
        texto1.setText(null);
        texto2.setText(null);
        texto3.setText(null);
        texto4.setText(null);
    }
    public void guardaraccion(){

    }

}