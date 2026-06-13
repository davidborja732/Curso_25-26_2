package org.iesch.ejercicio;

public class Producto {
    String nombre;
    Integer unidades;
    Integer precio;
    Integer subtotal;

    public Producto() {
    }

    public Producto(String nombre, Integer unidades, Integer precio, Integer subtotal) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getUnidades() {
        return unidades;
    }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
}
