package org.iesch.ejercicio_14;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HelloController1 {
    List<Producto> productos=new ArrayList<>();
    int contador=0;
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/piezas";
    static String user = "root";
    static String clave = "1234";
    String nombre = "";
    int id;
    double precio;
    @FXML
    TableView<Producto> tabla = new TableView<>();
    @FXML
    TableColumn<Producto, String> colId = new TableColumn<>("Id");
    @FXML
    TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
    @FXML
    TableColumn<Producto, String> colPrecio = new TableColumn<>("Precio");

    public HelloController1() throws SQLException {
    }


    @FXML
    protected void initialize() {
        Producto producto;
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        if (contador==0) {
            tabla.getColumns().addAll(colId,colNombre,colPrecio);
        }
        contador=1;
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Id,Nombre,Precio FROM piezas.piezas";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                id=(rs.getInt("Id"));
                nombre=(rs.getString("Nombre"));
                precio=(rs.getInt("Precio"));
                productos.add(producto=new Producto(id,nombre,precio));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        productos.forEach(productotabla -> tabla.getItems().add(productotabla));
    }
}