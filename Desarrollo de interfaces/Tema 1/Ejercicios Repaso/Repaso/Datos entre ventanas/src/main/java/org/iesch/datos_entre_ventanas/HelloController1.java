package org.iesch.datos_entre_ventanas;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.iesch.datos_entre_ventanas.HelloController.localidad;

public class HelloController1 {
    List<Persona> personas=new ArrayList<>();
    int contador=0;
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/datos";
    static String user = "root";
    static String clave = "1234";
    String nombre = "";
    String apellidos= "";
    String localidadusuario= "";
    int salario;
    @FXML
    TableView<Persona> tabla = new TableView<>();
    @FXML
    TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
    @FXML
    TableColumn<Persona, String> colApellido = new TableColumn<>("Apellido");
    @FXML
    TableColumn<Persona, String> colLocalidad = new TableColumn<>("Localidad");
    @FXML
    TableColumn<Persona, Integer> colSalario = new TableColumn<>("Salario");

    public HelloController1() throws SQLException {
    }


    @FXML
    protected void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        if (contador==0) {
            tabla.getColumns().addAll(colNombre, colApellido, colLocalidad, colSalario);
        }
        contador=1;
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * from datos.empleados where Localidad='"+localidad+"';";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                nombre=(rs.getString("Nombre"));
                apellidos=(rs.getString("Apellidos"));
                localidadusuario=(rs.getString("Localidad"));
                salario=(rs.getInt("Salario"));
                personas.add(new Persona(nombre,apellidos,localidad,salario));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        personas.forEach(persona -> tabla.getItems().add(persona));
    }
}