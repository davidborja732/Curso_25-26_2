package org.iesch.informe_2_tablas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.HashMap;

public class HelloController {
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    String departamento = "";
    static String url = "jdbc:mysql://localhost:3306/empresa";
    static String user = "root";
    static String clave = "1234";
    @FXML
    private Button boton;
    @FXML
    private ComboBox<String> combodepartamento;

    public HelloController() throws SQLException {
    }

    public void initialize(){
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT DISTINCT Departamento FROM empresa.departamentos";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                departamento =(rs.getString("Departamento"));
                System.out.println(departamento);
                combodepartamento.getItems().add(departamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void mostrarinforme() throws JRException {
        HashMap<String, Object> param=new HashMap<>();
        param.put("depart",combodepartamento.getValue());
        String fileRepo = "informes/Empleados_Nombre_Departamento.jasper";
        JasperPrint jpRepo = JasperFillManager.fillReport(fileRepo, param, con);
        JasperViewer viewer = new JasperViewer(jpRepo,false);
        viewer.setTitle("Informe empleados");
        viewer.setVisible(true);
    }
}
