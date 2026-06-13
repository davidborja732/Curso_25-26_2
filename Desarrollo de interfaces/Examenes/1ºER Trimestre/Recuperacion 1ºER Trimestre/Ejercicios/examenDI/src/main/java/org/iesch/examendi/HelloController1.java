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
    static String url = "jdbc:mysql://localhost:3306/poblacion";
    static String user = "root";
    static String clave = "1234";

    // Conexión activa a la BD
    Connection con = DriverManager.getConnection(url, user, clave);

    // Para almacenar resultados SQL
    ResultSet rs;

    // Lista donde se guardarán los empleados cargados desde la BD
    List<Empleados> empleados = new ArrayList<>();

    // Variables auxiliares para leer datos de la BD
    int id;
    String nombre;
    LocalDate fechaNac;
    int sexo;
    String departamento;
    int salario;

    // Elementos de la tabla en la interfaz
    @FXML
    private TableView<Empleados> tabla;

    @FXML
    private TableColumn<Empleados, Integer> colId;

    @FXML
    private TableColumn<Empleados, String> colNombre;

    @FXML
    private TableColumn<Empleados, String> colFecha;

    @FXML
    private TableColumn<Empleados, String> colSexo;

    @FXML
    private TableColumn<Empleados, String> colDepartamento;

    @FXML
    private TableColumn<Empleados, Integer> colSalario;

    @FXML
    private TableColumn<Empleados, Double> colSalarioNeto;

    // Constructor: solo lanza SQLException si falla la conexión
    public HelloController1() throws SQLException {}
    String sexoresultado;
    String nombredepartamneto;

    @FXML
    protected void initialize() {

        // -------------------------------
        // CONFIGURACIÓN DE LAS COLUMNAS
        // -------------------------------
        // Cada columna se vincula con el nombre EXACTO del getter en la clase Empleados
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colSalarioNeto.setCellValueFactory(new PropertyValueFactory<>("salarioNeto"));

        // -------------------------------
        // CARGAR EMPLEADOS DESDE LA BD
        // -------------------------------
        try {
            // Creamos un Statement para ejecutar la consulta
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            // Consulta SQL para obtener todos los empleados
            String sql = "SELECT Id,Nombre,FechaNac,Sexo,Departamento,Salario FROM empresa.empleados";
            rs = stat.executeQuery(sql);


            // Recorremos los resultados y creamos objetos Empleados
            while (rs.next()) {
                id = rs.getInt("Id");
                nombre = rs.getString("Nombre");
                fechaNac = rs.getDate("FechaNac").toLocalDate();
                sexo = rs.getInt("Sexo");
                departamento = rs.getString("Departamento");
                salario = rs.getInt("Salario");

                if (sexo == 1) {
                    sexoresultado="chica";
                }else {
                    sexoresultado="chico";
                }
                switch (departamento) {
                    case "1":
                        nombredepartamneto="Administracion";
                        break;
                    case "2":
                        nombredepartamneto="Contabilidad";
                        break;
                    case "3":
                        nombredepartamneto="Produccion";
                        break;
                    case "4":
                        nombredepartamneto="Comercial";
                        break;
                }
                // Crear el empleado con los datos de la BD
                Empleados emp = new Empleados(id, nombre, fechaNac, sexoresultado, nombredepartamneto, salario);

                // Calcular salario neto (por ejemplo, 85% del salario bruto)
                emp.setSalarioNeto(salario * 0.85);

                // Añadirlo a la lista
                empleados.add(emp);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tabla.getItems().addAll(empleados);
    }

    // Convierte el sexo numérico en texto
    public String obtenersexo(int sexopara) {
        return sexopara == 1 ? "Chica" : "Chico";
    }

    // Obtiene el nombre del departamento según su ID
    public String obtenernombre(int departamentoid) {
        try {
            Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT Departamento FROM empresa.departamentos WHERE Id=" + departamentoid;
            rs = stat.executeQuery(sql);

            if (rs.next()) {
                return rs.getString("Departamento");
            } else {
                return "desconocido";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}