package org.iesch.primer_informe_capitales;

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
    int actualpersona=0;
    StringBuilder sb = new StringBuilder();
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/poblacion";
    static String user = "root";
    static String clave = "1234";
    String provinciacombo;
    String autonomia;
    @FXML
    private ComboBox<String> Localidadusuario;
    @FXML
    private RadioButton normal;
    @FXML
    private RadioButton agrupado;
    @FXML
    private RadioButton InformeTabla;
    @FXML
    private Button boton;
    @FXML
    private Button boton2;
    @FXML
    private Button boton3;

    public HelloController() throws SQLException {
    }
    @FXML
    public void initialize() {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT DISTINCT Autonomía from poblacion.capitales";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                autonomia=(rs.getString("Autonomía"));
                System.out.println(autonomia);
                Localidadusuario.getItems().add(autonomia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void botonaccion() throws JRException {
        System.out.println(Localidadusuario.getValue());
        JasperDesign d = JRXmlLoader.load("informes/Informe_19_01_Capitales_Tabla.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT * from poblacion.capitales");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        JasperViewer.viewReport(jp,false);
    }@FXML
    public void botonaccion2() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("provin",Localidadusuario.getValue());
        System.out.println(Localidadusuario.getValue());
        String fileRepo = "informes/Informe_19_01_Capitales_Tabla.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, param, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }
    public void botonaccion3() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("provin",Localidadusuario.getValue());
        System.out.println(Localidadusuario.getValue());
        String fileRepo = "informes/CapitalesGrafico.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, param, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("TITULO INFORME");
        viewer.setVisible(true);
    }

}