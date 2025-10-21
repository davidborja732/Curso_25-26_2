package org.iesch.datos_entre_ventanas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    List<String> lista=new ArrayList<>();
    public static String localidad;
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/datos";
    static String user = "root";
    static String clave = "1234";
    @FXML
    private ComboBox<String> localidadeslegir;
    @FXML
    private Button botonsiguiente;

    public HelloController() throws SQLException {
    }


    @FXML
    protected void initialize() {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT DISTINCT(Localidad) from datos.empleados";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                lista.add(rs.getString("Localidad"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lista.forEach(s -> localidadeslegir.getItems().add(s));

    }
    public void AccionBoton(){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view-tabla.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 622, 487);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Personas de "+localidad);
        stage.setScene(scene);
        stage.show();
    }
    public void combo(){
        System.out.println(localidadeslegir.getValue());
        localidad=localidadeslegir.getValue();
    }
}