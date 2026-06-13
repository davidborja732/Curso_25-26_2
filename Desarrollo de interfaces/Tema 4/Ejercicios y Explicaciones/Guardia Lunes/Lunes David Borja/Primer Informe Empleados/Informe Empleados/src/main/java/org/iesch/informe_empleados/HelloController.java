package org.iesch.informe_empleados;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import java.sql.*;
import java.util.HashMap;

public class HelloController {
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    String localidad= "";
    static String url = "jdbc:mysql://localhost:3306/datos";
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
        System.setProperty("jasper.reports.compile.class.path", System.getProperty("java.class.path"));
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT DISTINCT Localidad FROM datos.empleados";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                localidad=(rs.getString("Localidad"));
                System.out.println(localidad);
                combolocalidades.getItems().add(localidad);
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
        }else if (agrupado.isSelected()){
            System.out.println("Grupal mostrando");
            informegrupal();
        }else if (calculado.isSelected()){
            System.out.println("Calculado mostrando");
            informeCalculado();
        }
    }
    @FXML
    public void informenormal() throws JRException {
        localidad=combolocalidades.getValue();
        HashMap<String, Object> param=new HashMap<>();
        param.put("rutaImagen", "imagenes/gatito.jpg");
        JasperDesign d = JRXmlLoader.load("informes/InfromeEmpleadosNoGrupo.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT * FROM datos.empleados WHERE Localidad='"+localidad+"' AND Salario >"+minimo.getText()+" AND Salario <"+maximo.getText());
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
        JasperViewer.viewReport(jp,false);
    }
    @FXML
    public void informegrupal() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("rutaImagen", "imagenes/gatito.jpg");
        localidad=combolocalidades.getValue();
        JasperDesign d = JRXmlLoader.load("informes/informeEmpleados.jrxml");

        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT * FROM datos.empleados WHERE Localidad='"+localidad+"' AND Salario >"+Double.parseDouble(minimo.getText())+" AND Salario <"+Double.parseDouble(maximo.getText()));
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,param,con);
        JasperViewer.viewReport(jp,false);
    }
    public void informeCalculado() throws JRException {
        String fileRepo = "informes/Calculado.jasper";

        JasperPrint jpRepo = JasperFillManager.fillReport(
                fileRepo,
                null,
                con
        );
        JasperViewer viewer = new JasperViewer(jpRepo, false);
        viewer.setTitle("Informe Calculado");
        viewer.setVisible(true);

    }






}