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
    int actualempleado;

    // Conexión a la base de datos
    Connection con = DriverManager.getConnection(url, user, clave);

    ResultSet rs;

    // Listas donde se guardan los departamentos y empleados cargados de la BD
    List<Departamento> listadepartamentos = new ArrayList<>();
    List<Empleados> empleados = new ArrayList<>();

    // Datos de conexión
    static String url = "jdbc:mysql://localhost:3306/empresa";
    static String user = "root";
    static String clave = "1234";

    // Variables auxiliares para cargar datos desde la BD
    int id;
    String nombre;
    LocalDate fechaNac;
    int sexo;
    String departamento;
    int salario;
    int iddepartamneto;
    String departamentonombre;

    // Elementos de la interfaz
    @FXML private TextField idtext;
    @FXML private TextField nombretext;
    @FXML private TextField salariotext;
    @FXML private ComboBox<String> departcombo;
    @FXML private RadioButton esmujer;
    @FXML private DatePicker datenacimiento;
    @FXML private RadioButton eshombre;

    public HelloController() throws SQLException {}

    @FXML
    protected void initialize() {
        // Limpia la lista para evitar duplicados al recargar
        empleados.clear();

        // Carga los departamentos en el ComboBox
        cargardepartementos();

        try {
            // Crea un Statement para leer los empleados
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Consulta SQL para obtener todos los empleados
            String sql = "SELECT Id,Nombre,FechaNac,Sexo,Departamento,Salario from empresa.empleados";
            rs = stat.executeQuery(sql);

            // Recorre los resultados y los añade a la lista
            while (rs.next()) {
                id = rs.getInt("Id");
                nombre = rs.getString("Nombre");
                fechaNac = rs.getDate("FechaNac").toLocalDate();
                sexo = rs.getInt("Sexo");
                departamento = rs.getString("Departamento");
                salario = rs.getInt("Salario");

                empleados.add(new Empleados(id, nombre, fechaNac, String.valueOf(sexo), departamento, salario));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Si hay empleados, muestra el primero
        if (!empleados.isEmpty()) {
            Empleados empleboton = empleados.get(actualempleado);

            idtext.setText(String.valueOf(empleboton.getId()));
            nombretext.setText(empleboton.getNombre());
            datenacimiento.setValue(empleboton.fechaNac);

            // Marca el radio button según el sexo
            if (Integer.parseInt(empleboton.getSexo()) == 1) {
                esmujer.fire();
            } else {
                eshombre.fire();
            }

            // Selecciona el departamento correspondiente
            departcombo.setValue(getNombreDepartamentoPorId(Integer.parseInt(empleboton.getDepartamento())));

            // Muestra el salario
            salariotext.setText(String.valueOf(empleboton.getSalario()));
        }
    }

    public void cargardepartementos() {

        // Limpia el ComboBox y la lista
        departcombo.getItems().clear();
        listadepartamentos.clear();

        try {
            // Carga los departamentos desde la BD
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Id,Departamento from empresa.departamentos";
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                iddepartamneto = rs.getInt("Id");
                departamentonombre = rs.getString("Departamento");
                listadepartamentos.add(new Departamento(iddepartamneto, departamentonombre));
            }

            // Añade los nombres al ComboBox
            listadepartamentos.forEach(d -> departcombo.getItems().add(d.getDepartamento()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Devuelve el nombre del departamento según su ID
    public String getNombreDepartamentoPorId(int idBuscado) {
        String sql = "SELECT Departamento FROM empresa.departamentos WHERE Id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idBuscado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Departamento");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Devuelve el ID del departamento según su nombre
    public int obtenerid(String departamento) {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Id from empresa.departamentos WHERE Departamento='" + departamento + "'";
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

        // Si ya estás en el primero, avisa
        if (actualempleado == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Empleados");
            alert.setContentText("Ya estás en el primer empleado");
            alert.show();
        } else {

            // Cambia al primer registro
            actualempleado = 0;
            Empleados empleboton = empleados.get(actualempleado);

            // Muestra los datos
            idtext.setText(String.valueOf(empleboton.getId()));
            nombretext.setText(empleboton.getNombre());
            datenacimiento.setValue(empleboton.fechaNac);

            if (Integer.parseInt(empleboton.getSexo()) == 1) {
                esmujer.fire();
            } else {
                eshombre.fire();
            }

            salariotext.setText(String.valueOf(empleboton.getSalario()));
            departcombo.setValue(getNombreDepartamentoPorId(Integer.parseInt(empleboton.getDepartamento())));
        }
    }

    public void setRegistroAnterior() {

        // Si ya estás en el primero, avisa
        if (actualempleado == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Empleados");
            alert.setContentText("Ya estás en el primer empleado");
            alert.show();
        } else {

            // Retrocede un registro
            actualempleado--;
            Empleados empleboton = empleados.get(actualempleado);

            // Muestra los datos
            idtext.setText(String.valueOf(empleboton.getId()));
            nombretext.setText(empleboton.getNombre());
            datenacimiento.setValue(empleboton.fechaNac);

            if (Integer.parseInt(empleboton.getSexo()) == 1) {
                esmujer.fire();
            } else {
                eshombre.fire();
            }

            salariotext.setText(String.valueOf(empleboton.getSalario()));
            departcombo.setValue(getNombreDepartamentoPorId(Integer.parseInt(empleboton.getDepartamento())));
        }
    }

    public void setRegistroSiguiente() {

        // Si ya estás en el último, avisa
        if (actualempleado == empleados.size() - 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Empleados");
            alert.setContentText("Ya estás en el último empleado");
            alert.show();
        } else {

            // Avanza un registro
            actualempleado++;
            Empleados empleboton = empleados.get(actualempleado);

            // Muestra los datos
            idtext.setText(String.valueOf(empleboton.getId()));
            nombretext.setText(empleboton.getNombre());
            datenacimiento.setValue(empleboton.fechaNac);

            if (Integer.parseInt(empleboton.getSexo()) == 1) {
                esmujer.fire();
            } else {
                eshombre.fire();
            }

            salariotext.setText(String.valueOf(empleboton.getSalario()));
            departcombo.setValue(getNombreDepartamentoPorId(Integer.parseInt(empleboton.getDepartamento())));
        }
    }

    public void setUltimoRegistro() {

        // Si ya estás en el último, avisa
        if (actualempleado == empleados.size() - 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ver Empleados");
            alert.setContentText("Ya estás en el último empleado");
            alert.show();
        } else {

            // Cambia al último registro
            actualempleado = empleados.size() - 1;
            Empleados empleboton = empleados.get(actualempleado);

            // Muestra los datos
            idtext.setText(String.valueOf(empleboton.getId()));
            nombretext.setText(empleboton.getNombre());
            datenacimiento.setValue(empleboton.fechaNac);

            if (Integer.parseInt(empleboton.getSexo()) == 1) {
                esmujer.fire();
            } else {
                eshombre.fire();
            }

            salariotext.setText(String.valueOf(empleboton.getSalario()));
            departcombo.setValue(getNombreDepartamentoPorId(Integer.parseInt(empleboton.getDepartamento())));
        }
    }

    // Limpia los campos para crear un nuevo empleado
    public void SetNuevoEmpleado() {
        Empleados empleados1 = empleados.getLast();

        idtext.setText(String.valueOf(empleados1.getId() + 1));
        idtext.setEditable(false);

        nombretext.clear();
        salariotext.clear();
        esmujer.setSelected(false);
        eshombre.setSelected(false);
        datenacimiento.setValue(null);
        departcombo.getSelectionModel().clearSelection();
    }

    // Guarda un nuevo empleado en la BD
    public void guardaraccion() {

        // VALIDACIONES DE CAMPOS VACÍOS
        if (idtext.getText().isEmpty() ||
                nombretext.getText().isEmpty() ||
                salariotext.getText().isEmpty() ||
                datenacimiento.getValue() == null ||
                departcombo.getValue() == null ||
                (!esmujer.isSelected() && !eshombre.isSelected())) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validación");
            alert.setHeaderText("Faltan datos obligatorios");
            alert.setContentText("Debes rellenar todos los campos antes de guardar.");
            alert.show();
            return;
        }

        // Validar salario numérico
        int salarioInt;
        try {
            salarioInt = Integer.parseInt(salariotext.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en salario");
            alert.setContentText("El salario debe ser un número válido.");
            alert.show();
            return;
        }

        // Obtener sexo
        int sexo = eshombre.isSelected() ? 0 : 1;

        try  {
            // Inserta el empleado en la BD
            PreparedStatement stmt = con.prepareStatement("Insert into empleados VALUES (?,?,?,?,?,?)");
            stmt.setString(1, idtext.getText());
            stmt.setString(2, nombretext.getText());
            stmt.setDate(3, Date.valueOf(datenacimiento.getValue()));
            stmt.setString(4, String.valueOf(sexo));
            stmt.setString(5, String.valueOf(obtenerid(departcombo.getValue())));
            stmt.setInt(6, salarioInt);

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insertar empleado");
                alert.setContentText("Empleado insertado correctamente");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Insertar empleado");
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
            scene = new Scene(fxmlLoader.load(), 683, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setTitle("Empleados");
        stage.setScene(scene);
        stage.show();
    }
}
