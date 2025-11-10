package org.iesch.ej_11;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    List<Libro> libros=new ArrayList<>();
    Connection con = DriverManager.getConnection(url, user, clave);
    int actualpersona=0;
    ResultSet rs;
    int id;
    String titulo= "";
    String autor= "";
    String isbn="";
    int paginas;
    int genero;
    int disponible;
    static String url = "jdbc:mysql://localhost:3306/libros";
    static String user = "root";
    static String clave = "1234";
    @FXML
    private Button generosboton;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;
    @FXML
    private Button boton4;
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private TextField texto3;
    @FXML
    private TextField texto4;
    @FXML
    private TextField texto5;
    @FXML
    private TextField texto6;
    @FXML
    private CheckBox texto7;


    public HelloController() throws SQLException {
    }

    @FXML
    public void initialize() {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Id,Titulo,Autor,ISBN,Paginas,Genero,Disponible FROM libros.libros";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                id=(rs.getInt("Id"));
                titulo=(rs.getString("Titulo"));
                autor=(rs.getString("Autor"));
                isbn=(rs.getString("ISBN"));
                paginas=(rs.getInt("Paginas"));
                genero=(rs.getInt("Genero"));
                disponible=(rs.getInt("Disponible"));
                Libro libro=new Libro(id,titulo,autor,isbn,paginas,genero,disponible);
                System.out.println(libro);
                libros.add(libro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Libro libroboton=libros.get(actualpersona);
        texto1.setText(String.valueOf(libroboton.getId()));
        texto2.setText(libroboton.getTitulo());
        texto3.setText(libroboton.getAutor());
        texto4.setText(libroboton.getISBN());
        texto5.setText(String.valueOf(libroboton.getPaginas()));
        texto6.setText(obtenergenero(libroboton.getGenero()));
        texto7.setSelected(trueofalse(libroboton.Disponible));

    }
    @FXML
    public void setBoton1(){
        if (actualpersona==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Libros");
            alert.setContentText("Ya estas en el primer libro");
            alert.show();
        }else {
            actualpersona=0;
            Libro libroboton=libros.get(actualpersona);
            texto1.setText(String.valueOf(libroboton.getId()));
            texto2.setText(libroboton.getTitulo());
            texto3.setText(libroboton.getAutor());
            texto4.setText(libroboton.getISBN());
            texto5.setText(String.valueOf(libroboton.getPaginas()));
            texto6.setText(obtenergenero(libroboton.getGenero()));
            texto7.setSelected(trueofalse(libroboton.Disponible));
        }
    }
    public void setBoton2(){
        if (actualpersona==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Libros");
            alert.setContentText("Ya estas en el primer libro");
            alert.show();
        }else {
            actualpersona-=1;
            Libro libroboton=libros.get(actualpersona);
            texto1.setText(String.valueOf(libroboton.getId()));
            texto2.setText(libroboton.getTitulo());
            texto3.setText(libroboton.getAutor());
            texto4.setText(libroboton.getISBN());
            texto5.setText(String.valueOf(libroboton.getPaginas()));
            texto6.setText(obtenergenero(libroboton.getGenero()));
            texto7.setSelected(trueofalse(libroboton.Disponible));
        }
    }
    public void setBoton3(){
        if (actualpersona==libros.size()-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Libros");
            alert.setContentText("Ya estas en el ultimo libro");
            alert.show();
        }else {
            actualpersona+=1;
            Libro libroboton=libros.get(actualpersona);
            texto1.setText(String.valueOf(libroboton.getId()));
            texto2.setText(libroboton.getTitulo());
            texto3.setText(libroboton.getAutor());
            texto4.setText(libroboton.getISBN());
            texto5.setText(String.valueOf(libroboton.getPaginas()));
            texto6.setText(obtenergenero(libroboton.getGenero()));
            texto7.setSelected(trueofalse(libroboton.Disponible));
        }
    }
    public void setBoton4(){
        if (actualpersona==libros.size()-1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver libros");
            alert.setContentText("Ya estas en el ultimo libro");
            alert.show();
        }else {
            actualpersona=libros.size()-1;
            Libro libroboton=libros.get(actualpersona);
            texto1.setText(String.valueOf(libroboton.getId()));
            texto2.setText(libroboton.getTitulo());
            texto3.setText(libroboton.getAutor());
            texto4.setText(libroboton.getISBN());
            texto5.setText(String.valueOf(libroboton.getPaginas()));
            texto6.setText(obtenergenero(libroboton.getGenero()));
            texto7.setSelected(trueofalse(libroboton.Disponible));
        }
    }
    public Boolean trueofalse(int numero){
        if (numero==0){
            return false;
        }else {
            return true;
        }
    }
    public String obtenergenero(int numero){
        String genero=null;
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT Nombre from libros.genero WHERE Id="+numero;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                genero=(rs.getString("Nombre"));
                return genero;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @FXML
    public void tablaAccion(){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view1.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 447, 436);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Generos");
        stage.setScene(scene);
        stage.show();
    }
}