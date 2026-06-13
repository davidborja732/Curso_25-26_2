package org.iesch.botonfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.RangeSlider;
import org.controlsfx.control.SearchableComboBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    public HelloController() throws SQLException {
    }

    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    List<Integer> lista=new ArrayList<>();
    List<Capitales> capitales=new ArrayList<>();
    static String url = "jdbc:mysql://localhost:3306/poblacion";
    static String user = "root";
    static String clave = "1234";
    String provincia;
    String autonomia;
    int poblacion;
    @FXML
    RangeSlider buscador;
    @FXML
    TableView<Capitales> tabla = new TableView<>();
    @FXML
    TableColumn<Capitales, String> colProvincia = new TableColumn<>("Provincia");
    @FXML
    TableColumn<Capitales, String> colAutonomia = new TableColumn<>("Autonomia");
    @FXML
    TableColumn<Capitales, Integer> colPoblacion = new TableColumn<>("Poblacion");
    @FXML
    protected void initialize() {
        buscador.setShowTickLabels(true);
        buscador.setShowTickMarks(true);
        buscador.setMajorTickUnit(500000);
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("Provincia"));
        colAutonomia.setCellValueFactory(new PropertyValueFactory<>("Autonomia"));
        colPoblacion.setCellValueFactory(new PropertyValueFactory<>("Poblacion"));
        tabla.getColumns().addAll(colProvincia, colAutonomia, colPoblacion);
        try {
            Statement stat;
            String sql;
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            sql = "SELECT * FROM poblacion.capitales";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                provincia=(rs.getString("Provincia"));
                autonomia=(rs.getString("Autonomía"));
                poblacion=(rs.getInt("Población"));
                capitales.add(new Capitales(provincia,autonomia,poblacion));
            }
            sql = "SELECT Población from poblacion.capitales WHERE Población=(SELECT MIN(Población) FROM poblacion.capitales)";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                lista.add(rs.getInt("Población"));
            }
            sql = "SELECT Población from poblacion.capitales WHERE Población=(SELECT MAX(Población) FROM poblacion.capitales)";
            rs = stat.executeQuery(sql);
            if (rs.next()) {
                lista.add(rs.getInt("Población"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(capitales);
        capitales.forEach(capital1 -> tabla.getItems().add(capital1));
        buscador.setMin(lista.get(0));
        buscador.setMax(lista.get(1));
        buscador.setHighValue(lista.get(1));
    }
    public void botonaction(){
        tabla.getItems().clear();
        capitales.clear();
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM poblacion.capitales WHERE Población>="+buscador.getLowValue()+"AND Población <="+buscador.getHighValue();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                provincia=(rs.getString("Provincia"));
                autonomia=(rs.getString("Autonomía"));
                poblacion=(rs.getInt("Población"));
                capitales.add(new Capitales(provincia,autonomia,poblacion));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        capitales.forEach(capital1 -> tabla.getItems().add(capital1));
    }
}