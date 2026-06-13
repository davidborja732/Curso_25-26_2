package org.iesch.datos_1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    int contador=0;
    List<Persona> personas=new ArrayList<>();
    Connection con = DriverManager.getConnection(url, user, clave);
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
    private Button botonanadir;
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private TextField texto3;
    @FXML
    private TextField texto4;
    @FXML
    TableView<Persona> tabla = new TableView();
    @FXML
    TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
    @FXML
    TableColumn<Persona, String> colApellido = new TableColumn<>("Apellido");
    @FXML
    TableColumn<Persona, String> colLocalidad = new TableColumn<>("Localidad");
    @FXML
    TableColumn<Persona, Integer> colSalario = new TableColumn<>("Salario");


    public HelloController() throws SQLException {
    }

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        if (contador==0) {
            tabla.getColumns().addAll(colNombre, colApellido, colLocalidad, colSalario);
        }
        System.out.println(contador);
        contador=1;
        System.out.println(contador);
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Nombre,Apellidos,Localidad,Salario from datos.empleados";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                nombre=(rs.getString("Nombre"));
                apellidos=(rs.getString("Apellidos"));
                localidad=(rs.getString("Localidad"));
                salario=(rs.getInt("Salario"));
                personas.add(new Persona(nombre,apellidos,localidad,salario));
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
        if (actualpersona==personas.size()-1){
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
        if (actualpersona==personas.size()-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver personas");
            alert.setContentText("Ya estas en la ultima persona");
            alert.show();
        }else {
            actualpersona=personas.size()-1;
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
        try  {
            PreparedStatement stmt = con.prepareStatement("Insert into empleados VALUES (?,?,?,?)");
            stmt.setString(1, texto1.getText());
            stmt.setString(2,texto2.getText());
            stmt.setString(3,texto3.getText());
            stmt.setInt(4,Integer.parseInt(texto4.getText()));
            int filas= stmt.executeUpdate();
            if (filas>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insertar personas");
                alert.setContentText("Dato insertado correctamente");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insertar personas");
                alert.setContentText("Insercion fallida");
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        personas.add(new Persona(texto1.getText(),texto2.getText(),texto3.getText(),Integer.parseInt(texto4.getText())));
        initialize();
        texto1.setText(null);
        texto2.setText(null);
        texto3.setText(null);
        texto4.setText(null);
        actualpersona=0;
        Persona personaboton=personas.get(actualpersona);
        texto1.setText(personaboton.nombre);
        texto2.setText(personaboton.apellidos);
        texto3.setText(personaboton.localidad);
        texto4.setText(String.valueOf(personaboton.salario));
    }
    public void anadiratabla(){
        Persona p1=new Persona(texto1.getText(),texto2.getText(),texto3.getText(),Integer.parseInt(texto4.getText()));
        tabla.getItems().add(p1);
    }

}