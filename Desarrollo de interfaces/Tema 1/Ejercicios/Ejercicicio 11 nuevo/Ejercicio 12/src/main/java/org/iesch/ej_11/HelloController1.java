package org.iesch.ej_11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController1 {
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/libros";
    static String user = "root";
    static String clave = "1234";
    String nombre="";
    int id;
    @FXML
    TableView<Genero> tabla = new TableView();
    @FXML
    TableColumn<Genero, Integer> colId = new TableColumn<>("Id");
    @FXML
    TableColumn<Genero, String> colNombre = new TableColumn<>("Nombre");


    public HelloController1() throws SQLException {
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tabla.getColumns().addAll(colId,colNombre);
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Id,Nombre FROM libros.genero";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                id=(rs.getInt("Id"));
                nombre=(rs.getString("Nombre"));
                tabla.getItems().add(new Genero(id,nombre));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}