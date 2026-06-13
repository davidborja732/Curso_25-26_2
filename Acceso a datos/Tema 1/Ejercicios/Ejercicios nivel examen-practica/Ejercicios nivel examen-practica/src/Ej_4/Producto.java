package Ej_4;

public class Producto {
    String nombre;
    Double precio;
    String categoria;

    public Producto() {
    }

    public Producto(Double precio, String categoria, String nombre) {
        this.precio = precio;
        this.categoria = categoria;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                '}';
    }
}
