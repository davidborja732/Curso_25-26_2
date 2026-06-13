package org.iesch.ejercicio_14;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HelloController {
    List<Producto> productos=new ArrayList<>();
    Connection con;
    {
        try {
            con = DriverManager.getConnection(url, user, clave);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int actualproducto =0;
    ResultSet rs;
    String nombre = "";
    int id;
    double precio;
    static String url = "jdbc:mysql://localhost:3306/piezas";
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
    private Button mostrartabla;
    @FXML
    private Button guardar;
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private TextField texto3;



    @FXML
    public void initialize() {
        texto1.setEditable(false);
        texto2.setEditable(false);
        texto3.setEditable(false);
        Producto producto;
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
        Producto productoboton=productos.get(actualproducto);
        texto1.setText(String.valueOf(productoboton.getId()));
        texto2.setText(productoboton.getNombre());
        texto3.setText(String.valueOf(productoboton.getPrecio()));

    }
    @FXML
    public void setBoton1(){
        if (actualproducto ==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Productos");
            alert.setContentText("Ya estas en el primer producto");
            alert.show();
        }else {
            actualproducto =0;
            Producto productoboton=productos.get(actualproducto);
            texto1.setText(String.valueOf(productoboton.getId()));
            texto2.setText(productoboton.getNombre());
            texto3.setText(String.valueOf(productoboton.getPrecio()));
        }
    }
    public void setBoton2(){
        if (actualproducto ==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Productos");
            alert.setContentText("Ya estas en el primer producto");
            alert.show();
        }else {
            actualproducto -=1;
            Producto productoboton=productos.get(actualproducto);
            texto1.setText(String.valueOf(productoboton.getId()));
            texto2.setText(productoboton.getNombre());
            texto3.setText(String.valueOf(productoboton.getPrecio()));
        }
    }
    public void setBoton3(){
        if (actualproducto ==productos.size()-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Productos");
            alert.setContentText("Ya estas en el ultimo producto");
            alert.show();
        }else {
            actualproducto +=1;
            Producto productoboton=productos.get(actualproducto);
            texto1.setText(String.valueOf(productoboton.getId()));
            texto2.setText(productoboton.getNombre());
            texto3.setText(String.valueOf(productoboton.getPrecio()));
        }
    }
    public void setBoton4(){
        if (actualproducto ==productos.size()-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Productos");
            alert.setContentText("Ya estas en el ultimo producto");
            alert.show();
        }else {
            actualproducto =productos.size()-1;
            Producto productoboton=productos.get(actualproducto);
            texto1.setText(String.valueOf(productoboton.getId()));
            texto2.setText(productoboton.getNombre());
            texto3.setText(String.valueOf(productoboton.getPrecio()));
        }
    }
    public void nuevoresgistroaccion(){
        Producto producto=productos.get(productos.size()-1);
        int maxkey=producto.getId() +1;
        actualproducto =1;
        texto1.setText(String.valueOf(maxkey));
        texto1.setEditable(false);
        texto2.setText(null);
        texto3.setText(null);
        texto2.setEditable(true);
        texto3.setEditable(true);
    }
    public void guardaraccion(){
        try  {
            PreparedStatement stmt = con.prepareStatement("Insert into piezas VALUES (?,?,?)");
            stmt.setString(1, texto1.getText());
            stmt.setString(2,texto2.getText());
            stmt.setString(3,texto3.getText());
            int filas= stmt.executeUpdate();
            if (filas>0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insertar productos");
                alert.setContentText("Dato insertado correctamente");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insertar producto");
                alert.setContentText("Insercion fallida");
                alert.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        productos.add(new Producto(Integer.valueOf(texto1.getText()),texto2.getText(),Double.valueOf(texto3.getText())));
        initialize();
        texto1.setText(null);
        texto2.setText(null);
        texto3.setText(null);
        actualproducto =0;
        Producto productoboton=productos.get(actualproducto);
        texto1.setText(String.valueOf(productoboton.getId()));
        texto2.setText(productoboton.getNombre());
        texto3.setText(String.valueOf(productoboton.getPrecio()));
    }
    public void setbotontabla(){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view-tabla.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 622, 487);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Productos");
        stage.setScene(scene);
        stage.show();
    }

}