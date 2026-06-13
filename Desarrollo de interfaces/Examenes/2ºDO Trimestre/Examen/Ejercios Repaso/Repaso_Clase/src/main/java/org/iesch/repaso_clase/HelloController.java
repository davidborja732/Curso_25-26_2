package org.iesch.repaso_clase;

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

import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

public class HelloController {
    Connection con = DriverManager.getConnection(url, user, clave);
    ResultSet rs;
    String categoria = "";
    static String url = "jdbc:mysql://localhost:3306/ventas";
    static String user = "root";
    static String clave = "1234";
    @FXML
    private ComboBox<String> combocategorias;
    @FXML
    private RadioButton productos;
    @FXML
    private RadioButton categorias;
    @FXML
    private RadioButton grafico;
    @FXML
    private Button boton;
    /*@FXML
    private TextField minimo;
    @FXML
    private TextField maximo;*/

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
                combocategorias.getItems().add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void botonaccion() throws JRException {
        if (!productos.isSelected() && !categorias.isSelected() && !grafico.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Informes");
            alert.setContentText("Selecciona alguno de los RadioButton");
            alert.show();
        } else if (combocategorias.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Informes");
            alert.setContentText("Selecciona alguna opcion en el ComboBox");
            alert.show();
        }else if (productos.isSelected()){
            System.out.println("Normal mostrando");
            informenormal();
        }else if (categorias.isSelected()){
            System.out.println("Grupal mostrando");
            informeGrupo();
        }else if (grafico.isSelected()){
            System.out.println("Calculado mostrando");
            informegrafico();
        }
    }
    @FXML
    public void informenormal() throws JRException {
        categoria=combocategorias.getValue();
        HashMap<String, Object> param=new HashMap<>();
        param.put("imagen", "imagenes/gatito.jpg");
        InputStream input = getClass().getResourceAsStream("/Informes/Ventas_Producto.jrxml");
        JasperDesign d = JRXmlLoader.load(input);
        //JasperDesign d = JRXmlLoader.load("informes/Ventas_Producto.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT  *,(PrecioUnidad*CantidadVendida) as Precio_total FROM ventas.ventas;");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = fillReport(jr,param,con);
        JasperViewer.viewReport(jp,false);
    }
    @FXML
    public void informeGrupo() throws JRException {
        categoria=combocategorias.getValue();
        /*HashMap<String, Object> param=new HashMap<>();
        param.put("imagen", "imagenes/gatito.jpg");*/
        InputStream input = getClass().getResourceAsStream("/Informes/Agrupacion_categoria.jrxml");
        JasperDesign d = JRXmlLoader.load(input);
        //JasperDesign d = JRXmlLoader.load("informes/Ventas_Producto.jrxml");
        JRDesignQuery jq = new JRDesignQuery();
        jq.setText("SELECT * FROM ventas.ventas order by `Categor?a`;");
        d.setQuery(jq);
        JasperReport jr = JasperCompileManager.compileReport(d);
        JasperPrint jp = fillReport(jr,null,con);
        JasperViewer.viewReport(jp,false);
    }
    @FXML
    public void informegrafico() throws JRException {
        categoria=combocategorias.getValue();
        HashMap<String, Object> param=new HashMap<>();
        param.put("cata", categoria);

        String fileRepo = "Informes/Grafico_Categoria.jasper";
        InputStream input = getClass().getResourceAsStream(fileRepo);
        JasperPrint jpRepo = JasperFillManager.fillReport(
                fileRepo,
                param,
                con
        );
        JasperViewer viewer = new JasperViewer(jpRepo, false);
        viewer.setTitle("Informe Grafico");
        viewer.setVisible(true);
    }
}