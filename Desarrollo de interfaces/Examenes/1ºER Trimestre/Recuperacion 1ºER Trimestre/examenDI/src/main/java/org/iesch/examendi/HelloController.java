package org.iesch.examendi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    // Índice del empleado actualmente mostrado en pantalla
    int actualproducto;
    Producto producboton;

    // Conexión a la base de datos
    Connection con = DriverManager.getConnection(url, user, clave);

    ResultSet rs;

    // Listas donde se guardan los departamentos y empleados cargados de la BD
    List<Categoria> categorias = new ArrayList<>();
    List<Producto> productos = new ArrayList<>();
    List<Marca> marcas=new ArrayList<>();

    // Datos de conexión
    static String url = "jdbc:mysql://localhost:3306/productos";
    static String user = "root";
    static String clave = "1234";

    // Variables auxiliares para cargar datos desde la BD
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

    // Elementos de la interfaz
    @FXML private TextField idtext;
    @FXML private TextField productotext;
    @FXML private TextField preciotext;
    @FXML private ComboBox<String> categoriacombo;
    @FXML private ComboBox<String> marcacombo;
    @FXML private CheckBox nfccheck;
    @FXML private CheckBox wificheck;
    @FXML private CheckBox bluetoothcheck;
    @FXML private CheckBox cincogcheck;
    @FXML private RadioButton esnuevo;
    @FXML private RadioButton esrecaondicionado;

    public HelloController() throws SQLException {}

    @FXML
    protected void initialize() {
        wificheck.setSelected(false);
        nfccheck.setSelected(false);
        bluetoothcheck.setSelected(false);
        cincogcheck.setSelected(false);
        // Limpia la lista para evitar duplicados al recargar
        productos.clear();

        // Carga los departamentos en el ComboBox
        cargarmarcas();
        cargarcategorias();

        try {
            //Id | Producto             | Precio | Categoria | Marca | Wifi | Bluetooth | NFC  | 5G   | Estado
            // Crea un Statement para leer los empleados
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Consulta SQL para obtener todos los empleados
            String sql = "SELECT Id,Producto ,Precio,Categoria,Marca,Wifi,Bluetooth,NFC,5G,Estado from productos.productos";
            rs = stat.executeQuery(sql);

            // Recorre los resultados y los añade a la lista
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
                Producto productolista=new Producto(id, producto, precio, categoria, marca, wifi,bluetooth,nfc,cincog,estado);
                System.out.println(productolista.toString());
                productos.add(productolista);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Si hay empleados, muestra el primero
        if (!productos.isEmpty()) {
            producboton = productos.get(actualproducto);
            idtext.setText(String.valueOf(producboton.getId()));
            productotext.setText(producboton.getProducto());
            preciotext.setText(String.valueOf(producboton.getPrecio()));
            categoriacombo.setValue(getNombreCategoriaPorId(producboton.getCategoria()));
            marcacombo.setValue(getNombreMarcaPorId(producboton.getMarca()));
            if (producboton.getWifi()==1){
                wificheck.setSelected(true);
            }
            if (producboton.getNfc()==1){
                nfccheck.setSelected(true);
            }
            if (producboton.getBluetooth()==1){
                bluetoothcheck.setSelected(true);
            }
            if (producboton.getCincog()==1){
                cincogcheck.setSelected(true);
            }

            // Marca el radio button según el sexo
            if (producboton.getEstado() == 1) {
                esrecaondicionado.fire();
            } else if (producboton.getEstado()==0){
                esnuevo.setSelected(true);
            }
        }
    }

    public void cargarmarcas() {
        int idmarca;
        String nombremarca;

        // Limpia el ComboBox y la lista
        marcacombo.getItems().clear();
        marcas.clear();

        try {
            // Carga los departamentos desde la BD
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Id,Marca from productos.marcas";
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                idmarca = rs.getInt("Id");
                nombremarca = rs.getString("Marca");
                marcas.add(new Marca(idmarca,nombremarca));
            }

            // Añade los nombres al ComboBox
            marcas.forEach(d -> marcacombo.getItems().add(d.getMarca()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void cargarcategorias() {
        int idcategoria;
        String nombrecategoria;

        // Limpia el ComboBox y la lista
        categoriacombo.getItems().clear();
        categorias.clear();

        try {
            // Carga los departamentos desde la BD
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Id,Categoria from productos.categorias";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                idcategoria = rs.getInt("Id");
                nombrecategoria = rs.getString("Categoria");
                categorias.add(new Categoria(idcategoria,nombrecategoria));
            }
            // Añade los nombres al ComboBox
            categorias.forEach(d -> categoriacombo.getItems().add(d.getCategoria()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Devuelve el nombre del departamento según su ID
    public String getNombreCategoriaPorId(int idBuscado) {
        String sql = "SELECT Categoria FROM productos.categorias WHERE Id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBuscado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Categoria");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public String getNombreMarcaPorId(int idBuscado) {
        String sql = "SELECT Marca FROM productos.marcas WHERE Id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBuscado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Marca");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Devuelve el ID del departamento según su nombre
    public int obteneridmarca(String marcabuscar) {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Id from productos.marcas WHERE Marca='" + marcabuscar + "'";
            rs = stat.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("Id");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int obteneridcategoria(String categoriabuscar) {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Id from productos.categorias WHERE categoria='" + categoriabuscar + "'";
            rs = stat.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("Id");
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // -------------------------
    // NAVEGACIÓN ENTRE REGISTROS
    // -------------------------

    @FXML
    public void setPrimerRegistro() {
        wificheck.setSelected(false);
        nfccheck.setSelected(false);
        bluetoothcheck.setSelected(false);
        cincogcheck.setSelected(false);
        // Si ya estás en el primero, avisa
        if (actualproducto == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Producto");
            alert.setContentText("Ya estás en el primer productos");
            alert.show();
        } else {
            // Cambia al primer registro
            actualproducto = 0;
            producboton = productos.get(actualproducto);
            idtext.setText(String.valueOf(producboton.getId()));
            productotext.setText(producboton.getProducto());
            preciotext.setText(String.valueOf(producboton.getPrecio()));
            categoriacombo.setValue(getNombreCategoriaPorId(producboton.getCategoria()));
            marcacombo.setValue(getNombreMarcaPorId(producboton.getMarca()));
            if (producboton.getWifi()==1){
                wificheck.setSelected(true);
            }
            if (producboton.getNfc()==1){
                nfccheck.setSelected(true);
            }
            if (producboton.getBluetooth()==1){
                bluetoothcheck.setSelected(true);
            }
            if (producboton.getCincog()==1){
                cincogcheck.setSelected(true);
            }

            // Marca el radio button según el sexo
            if (producboton.getEstado() == 1) {
                esrecaondicionado.fire();
            } else if (producboton.getEstado()==0){
                esnuevo.setSelected(true);
            }
        }
    }

    public void setRegistroAnterior() {
        wificheck.setSelected(false);
        nfccheck.setSelected(false);
        bluetoothcheck.setSelected(false);
        cincogcheck.setSelected(false);

        // Si ya estás en el primero, avisa
        if (actualproducto == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Producto");
            alert.setContentText("Ya estás en el primer productos");
            alert.show();
        } else {
            // Cambia al primer registro
            actualproducto -= 1;
            producboton = productos.get(actualproducto);
            idtext.setText(String.valueOf(producboton.getId()));
            productotext.setText(producboton.getProducto());
            preciotext.setText(String.valueOf(producboton.getPrecio()));
            categoriacombo.setValue(getNombreCategoriaPorId(producboton.getCategoria()));
            marcacombo.setValue(getNombreMarcaPorId(producboton.getMarca()));
            if (producboton.getWifi()==1){
                wificheck.setSelected(true);
            }
            if (producboton.getNfc()==1){
                nfccheck.setSelected(true);
            }
            if (producboton.getBluetooth()==1){
                bluetoothcheck.setSelected(true);
            }
            if (producboton.getCincog()==1){
                cincogcheck.setSelected(true);
            }

            // Marca el radio button según el sexo
            if (producboton.getEstado() == 1) {
                esrecaondicionado.fire();
            } else if (producboton.getEstado()==0){
                esnuevo.setSelected(true);
            }
        }
    }

    public void setRegistroSiguiente() {
        wificheck.setSelected(false);
        nfccheck.setSelected(false);
        bluetoothcheck.setSelected(false);
        cincogcheck.setSelected(false);
        if (actualproducto == productos.size()-1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Producto");
            alert.setContentText("Ya estás en el primer productos");
            alert.show();
        } else {
            // Cambia al primer registro
            actualproducto +=1;
            producboton = productos.get(actualproducto);
            idtext.setText(String.valueOf(producboton.getId()));
            productotext.setText(producboton.getProducto());
            preciotext.setText(String.valueOf(producboton.getPrecio()));
            categoriacombo.setValue(getNombreCategoriaPorId(producboton.getCategoria()));
            marcacombo.setValue(getNombreMarcaPorId(producboton.getMarca()));
            if (producboton.getWifi()==1){
                wificheck.setSelected(true);
            }
            if (producboton.getNfc()==1){
                nfccheck.setSelected(true);
            }
            if (producboton.getBluetooth()==1){
                bluetoothcheck.setSelected(true);
            }
            if (producboton.getCincog()==1){
                cincogcheck.setSelected(true);
            }

            // Marca el radio button según el sexo
            if (producboton.getEstado() == 1) {
                esrecaondicionado.fire();
            } else if (producboton.getEstado()==0){
                esnuevo.setSelected(true);
            }
        }
    }

    public void setUltimoRegistro() {
        wificheck.setSelected(false);
        nfccheck.setSelected(false);
        bluetoothcheck.setSelected(false);
        cincogcheck.setSelected(false);
        if (actualproducto == productos.size()-1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Producto");
            alert.setContentText("Ya estás en el primer productos");
            alert.show();
        } else {
            // Cambia al primer registro
            actualproducto = productos.size()-1;
            Producto producboton = productos.get(actualproducto);
            System.out.println(producboton.toString());
            idtext.setText(String.valueOf(producboton.getId()));
            productotext.setText(producboton.getProducto());
            preciotext.setText(String.valueOf(producboton.getPrecio()));
            categoriacombo.setValue(getNombreCategoriaPorId(producboton.getCategoria()));
            marcacombo.setValue(getNombreMarcaPorId(producboton.getMarca()));
            if (producboton.getWifi()==1){
                wificheck.setSelected(true);
            }
            if (producboton.getNfc()==1){
                nfccheck.setSelected(true);
            }
            if (producboton.getBluetooth()==1){
                bluetoothcheck.setSelected(true);
            }
            if (producboton.getCincog()==1){
                cincogcheck.setSelected(true);
            }

            // Marca el radio button según el sexo
            if (producboton.getEstado() == 1) {
                esrecaondicionado.fire();
            } else if (producboton.getEstado()==0){
                esnuevo.setSelected(true);
            }
        }
    }

    // Limpia los campos para crear un nuevo empleado
    public void SetNuevoProducto() {
        Producto empleados1 = productos.getLast();

        idtext.setText(String.valueOf(empleados1.getId() + 1));
        idtext.setEditable(false);

        productotext.clear();
        preciotext.clear();
        esrecaondicionado.setSelected(false);
        esnuevo.setSelected(false);

        categoriacombo.getSelectionModel().clearSelection();
        marcacombo.getSelectionModel().clearSelection();
        wificheck.setSelected(false);
        nfccheck.setSelected(false);
        bluetoothcheck.setSelected(false);
        cincogcheck.setSelected(false);
    }

    // Guarda un nuevo empleado en la BD
    public void guardaraccion() {
        int wifiactivo;
        int nfcactivo;
        int blueactivo;
        int cincogactivo;
        int estadoguardar;
        if (wificheck.isArmed()){
            wifiactivo=1;
        }else {
            wifiactivo=0;
        }
        if (bluetoothcheck.isArmed()){
            blueactivo=1;
        }else {
            blueactivo=0;
        }
        if (nfccheck.isArmed()){
            nfcactivo=1;
        }else {
            nfcactivo=0;
        }
        if (cincogcheck.isArmed()){
            cincogactivo=1;
        }else {
            cincogactivo=0;
        }
        if (esnuevo.isArmed()){
           estadoguardar=0;
        }else {
            estadoguardar=1;
        }


        try  {
            // Inserta el empleado en la BD
            PreparedStatement stmt = con.prepareStatement("Insert into productos.productos VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, idtext.getText());
            stmt.setString(2, productotext.getText());
            stmt.setDouble(3, Double.parseDouble(preciotext.getText()));
            stmt.setInt(4, obteneridcategoria(categoriacombo.getValue()));
            stmt.setInt(5, obteneridmarca(marcacombo.getValue()));
            stmt.setInt(6, wifiactivo);
            stmt.setInt(7, blueactivo);
            stmt.setInt(8, nfcactivo);
            stmt.setInt(9, cincogactivo);
            stmt.setInt(10, estadoguardar);


            int filas = stmt.executeUpdate();

            if (filas > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insertar producto");
                alert.setContentText("Producto insertado correctamente");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insertar productoo");
                alert.setContentText("Inserción fallida");
                alert.show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Recarga la lista y muestra el último registro
        initialize();
        setUltimoRegistro();
    }

    // Abre la ventana de listados
    @FXML
    public void setMenuListados(){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view-tabla.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 1060, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Producto");
        stage.setScene(scene);
        stage.show();
    }
}
