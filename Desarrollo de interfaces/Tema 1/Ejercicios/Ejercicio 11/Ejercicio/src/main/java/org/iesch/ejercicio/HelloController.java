package org.iesch.ejercicio;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    List<Integer> lista=new ArrayList<>();
    int suma=0;
    @FXML
    private Button boton1;
    @FXML
    private Button boton2;
    @FXML
    private Label subtotalverdad;
    @FXML
    private TextField texto1;
    @FXML
    private TextField texto2;
    @FXML
    private TextField texto3;
    @FXML
    TableView<Producto> tabla = new TableView<>();
    @FXML
    TableColumn<Producto,String> colNombre = new TableColumn<>("Nombre");
    @FXML
    TableColumn<Producto,Integer> colUnidades = new TableColumn<>("Unidades");
    @FXML
    TableColumn<Producto,Integer> colPrecio = new TableColumn<>("Precio");
    @FXML
    TableColumn<Producto,Integer> colSubtotal = new TableColumn<>("Subtotal");

    @FXML
    protected void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tabla.getColumns().addAll(colNombre, colUnidades, colPrecio, colSubtotal);
        boton1.setOnAction(event -> agregar());
        boton2.setOnAction(event -> borrar());

    }
    @FXML
    public void agregar(){
        Producto producto=new Producto(texto1.getText(),Integer.valueOf(texto2.getText()),Integer.valueOf(texto3.getText()),Integer.valueOf(texto2.getText())*Integer.valueOf(texto3.getText()));
        tabla.getItems().add(producto);
        anadir(producto.subtotal);
    }
    @FXML
    public void borrar(){
        texto1.clear();
        texto2.clear();
        texto3.clear();
    }
    public void anadir(Integer subtotaltotal){
        suma=suma+subtotaltotal;
        subtotalverdad.setText(String.valueOf(suma));
    }
}