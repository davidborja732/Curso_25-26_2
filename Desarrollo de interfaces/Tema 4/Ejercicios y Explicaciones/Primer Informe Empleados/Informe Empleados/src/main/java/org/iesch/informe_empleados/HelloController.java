package org.iesch.informe_empleados;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

public class HelloController {
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    String categoria = "";
    static String url = "jdbc:mysql://localhost:3306/ventas";
    static String user = "root";
    static String clave = "1234";
    @FXML
    private ComboBox<String> combolocalidades;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton agrupado;
    @FXML
    private RadioButton calculado;
    @FXML
    private Button boton;
    @FXML
    private TextField minimo;
    @FXML
    private TextField maximo;

    public HelloController() throws SQLException {
    }

    @FXML
    public void initialize(){
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT DISTINCT `Categor?a` FROM ventas.ventas";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                categoria=(rs.getString("Categor?a"));
                System.out.println(categoria);
                combolocalidades.getItems().add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void botonaccion() throws JRException {
        if (!normal.isSelected() && !agrupado.isSelected() && !calculado.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Informes");
            alert.setContentText("Selecciona alguno de los RadioButton");
            alert.show();
        } else if (combolocalidades.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Informes");
            alert.setContentText("Selecciona alguna opcion en el ComboBox");
            alert.show();
        } else if (maximo.getText()==null || minimo.getText()==null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Informes");
            alert.setContentText("Pon un valor minimo y maximo");
            alert.show();
        } else if (normal.isSelected()){
            System.out.println("Normal mostrando");
            informenormal();
        }/*else if (agrupado.isSelected()){
            System.out.println("Grupal mostrando");
            informegrupal();
        }else if (calculado.isSelected()){
            System.out.println("Calculado mostrando");
            informeCalculado();
        }*/
    }
    @FXML
    public void informenormal() throws JRException {
        categoria=combolocalidades.getValue();
        HashMap<String, Object> param=new HashMap<>();
        param.put("imagen", "imagenes/gatito.jpg");
        InputStream input = getClass().getResourceAsStream("/informes/Ventas_Producto.jrxml");
        JasperDesign d = JRXmlLoader.load(input);
        //JasperDesign d = JRXmlLoader.load("informes/Ventas_Producto.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT  *,(PrecioUnidad*CantidadVendida) as Precio_total FROM ventas.ventas;");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
        JasperViewer.viewReport(jp,false);
    }


}