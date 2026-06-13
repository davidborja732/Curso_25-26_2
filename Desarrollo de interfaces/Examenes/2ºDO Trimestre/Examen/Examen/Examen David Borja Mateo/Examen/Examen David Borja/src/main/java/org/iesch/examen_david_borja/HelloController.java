package org.iesch.examen_david_borja;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.fill.*;


import java.sql.*;
import java.util.HashMap;

public class HelloController {
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/examen";
    static String user = "root";
    static String clave = "1234";
    String tipocargar;
    String provecargar;
    @FXML
    private ComboBox<String> tipousuario;
    @FXML
    private ComboBox<String> proveedorusuario;

    public HelloController() throws SQLException {
    }
    @FXML
    public void initialize() {
        String sql = "SELECT DISTINCT Tipo from examen.tipos";
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                tipocargar =(rs.getString("Tipo"));
                System.out.println(tipocargar);
                tipousuario.getItems().add(tipocargar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        sql = "SELECT DISTINCT Proveedor from examen.proveedores";
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                provecargar =(rs.getString("Proveedor"));
                System.out.println(provecargar);
                proveedorusuario.getItems().add(provecargar);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void Informe1() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("tipoparam",tipousuario.getValue());
        if (tipousuario.getValue().equals("Legumbres")){
            param.put("imagen","imagenes/Legumbres.jpg");
        } else if (tipousuario.getValue().equals("Quesos")){
            param.put("imagen","imagenes/Quesos.jpg");
        } else {
            param.put("imagen","imagenes/dulces.jpg");
        }
        System.out.println(tipousuario.getValue());
        String fileRepo = "informes/Informe1Examen.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo,param, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Informe 1");
        viewer.setVisible(true);
    }
    public void Informe2() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("proveedorparam",proveedorusuario.getValue());
        System.out.println(proveedorusuario.getValue());
        String fileRepo = "informes/Informe2Examen.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo,param, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Informe 2");
        viewer.setVisible(true);
    }
    public void Informe3() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("imagen","imagenes/logo.jpg");
        String fileRepo = "informes/Informe3Examen.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo,param, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Informe 3");
        viewer.setVisible(true);
    }
}