package org.iesch.examendi;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelloController1 {

    // Datos de conexión a la base de datos
    static String url = "jdbc:mysql://localhost:3306/productos";
    static String user = "root";
    static String clave = "1234";

    // Conexión activa a la BD
    Connection con = DriverManager.getConnection(url, user, clave);

    // Para almacenar resultados SQL
    ResultSet rs;

    // Lista donde se guardarán los empleados cargados desde la BD
    List<Producto> productos = new ArrayList<>();

    // Variables auxiliares para leer datos de la BD
    int id;
    String producto;
    double precio;
    int categoria;
    int marca;
    int wifi;
    int bluetooth;
    int nfc;
    int cincog;
    int estado;
    double precioiva;


    @FXML
    private TableView<Productotabla> tabla;

    @FXML
    private TableColumn<Productotabla, Integer> colId;

    @FXML
    private TableColumn<Productotabla, String> colProducto;

    @FXML
    private TableColumn<Productotabla, Double> colPrecio;
    @FXML
    private TableColumn<Productotabla, Double> colPrecioIva;
    @FXML
    private TableColumn<Productotabla, String> colCategoria;

    @FXML
    private TableColumn<Productotabla, String> colMarca;

    @FXML
    private TableColumn<Productotabla, String> colWifi;

    @FXML
    private TableColumn<Productotabla, String> colBluetooth;
    @FXML
    private TableColumn<Productotabla, String> colNFC;
    @FXML
    private TableColumn<Productotabla, String> col5g;
    @FXML
    private TableColumn<Productotabla, String> colEstado;



    // Constructor: solo lanza SQLException si falla la conexión
    public HelloController1() throws SQLException {}
    String sexoresultado;
    String nombredepartamneto;

    @FXML
    protected void initialize() {
        String nombrecategoria = "";
        String nombremarca= "";
        String tienewifi= "";
        String tienenfc= "";
        String tieneblue= "";
        String tiene5g= "";
        int estado;
        String estadonombre = "";

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colPrecioIva.setCellValueFactory(new PropertyValueFactory<>("precioiva"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        colWifi.setCellValueFactory(new PropertyValueFactory<>("wifi"));
        colBluetooth.setCellValueFactory(new PropertyValueFactory<>("bluetooth"));
        colNFC.setCellValueFactory(new PropertyValueFactory<>("nfc"));
        col5g.setCellValueFactory(new PropertyValueFactory<>("cincog"));
        colWifi.setCellValueFactory(new PropertyValueFactory<>("wifi"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        try {
            // Creamos un Statement para ejecutar la consulta
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Consulta SQL para obtener todos los empleados
            String sql = "SELECT Id,Producto,Precio,Categoria,Marca,Wifi,Bluetooth,NFC,5G,Estado FROM productos.productos";
            rs = stat.executeQuery(sql);


            // Recorremos los resultados y creamos objetos Producto
            while (rs.next()) {
                id = rs.getInt("Id");
                producto = rs.getString("producto");
                precio = rs.getDouble("Precio");
                categoria = rs.getInt("Categoria");
                marca = rs.getInt("Marca");
                wifi = rs.getInt("Wifi");
                bluetooth = rs.getInt("Bluetooth");
                nfc = rs.getInt("NFC");
                cincog = rs.getInt("5G");
                estado=rs.getInt("Estado");
                Producto productolista=new Producto(id, producto, precio, categoria, marca, wifi,bluetooth,nfc,cincog,estado,precio*1.21);
                switch (categoria){
                    case 1 :
                        nombrecategoria="Smartphone ";
                        break;
                    case 2 :
                        nombrecategoria="Tablet";
                        break;
                    case 3 :
                        nombrecategoria="Ordenador";
                        break;
                    case 4 :
                        nombrecategoria="Portatil";
                        break;
                }
                switch (marca){
                    case 1 :
                        nombremarca="Samsung ";
                        break;
                    case 2 :
                        nombremarca="HP";
                        break;
                    case 3 :
                        nombremarca="Xiaomi";
                        break;
                    case 4 :
                        nombremarca="Huawei";
                        break;
                    case 5 :
                        nombremarca="Lenovo";
                        break;
                    case 6 :
                        nombremarca="Asus";
                        break;
                }
                switch (wifi){
                    case 0 :
                        tienewifi="NO";
                        break;
                    case 1 :
                        tienewifi="SI";
                        break;
                }
                switch (bluetooth){
                    case 0 :
                        tieneblue="NO";
                        break;
                    case 1 :
                        tieneblue="SI";
                        break;
                }
                switch (nfc){
                    case 0 :
                        tienenfc="NO";
                        break;
                    case 1 :
                        tienenfc="SI";
                        break;
                }
                switch (cincog){
                    case 0 :
                        tiene5g="SI";
                        break;
                    case 1 :
                        tiene5g="NO";
                        break;
                }
                switch (estado){
                    case 0 :
                        estadonombre="Nuevo";
                        break;
                    case 1 :
                        estadonombre="Reacondicionado";
                        break;
                }
                Productotabla productotabla=new Productotabla(id,producto,precio,nombrecategoria,nombremarca,tienewifi,tieneblue,tienenfc,tiene5g,estadonombre,precio*1.21);
                tabla.getItems().add(productotabla);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}