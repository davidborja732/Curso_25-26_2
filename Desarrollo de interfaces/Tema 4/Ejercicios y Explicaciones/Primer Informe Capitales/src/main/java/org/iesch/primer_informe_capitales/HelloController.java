package org.iesch.primer_informe_capitales;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.sql.*;

public class HelloController {
    Connection con = DriverManager.getConnection(url, user, clave);
    int actualpersona=0;
    StringBuilder sb = new StringBuilder();
    ResultSet rs;
    static String url = "jdbc:mysql://localhost:3306/poblacion";
    static String user = "root";
    static String clave = "1234";
    String provinciacombo;
    @FXML
    private ComboBox<String> Localidadusuario;
    @FXML
    private RadioButton InformeNormal;
    @FXML
    private ComboBox<String> InformeAgrupado;
    @FXML
    private Button boton;

    public HelloController() throws SQLException {
    }

    public void initialize() {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT UNIQUE(Provincia) from poblacion.capitales";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                nombre=(rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}