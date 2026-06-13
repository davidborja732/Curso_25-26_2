package org.iesch.examendi;

import java.time.LocalDate;

public class Producto {
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
    private double precioiva;
    public Producto() {
    }

    public Producto(int id, String producto, double precio, int categoria, int marca, int wifi, int bluetooth, int nfc, int estado, int cincog) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.wifi = wifi;
        this.bluetooth = bluetooth;
        this.nfc = nfc;
        this.estado = estado;
        this.cincog = cincog;
    }

    public Producto(int id, String producto, double precio, int categoria, int marca, int wifi, int bluetooth, int nfc, int cincog, int estado, double precioiva) {
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.categoria = categoria;
        this.marca = marca;
        this.wifi = wifi;
        this.bluetooth = bluetooth;
        this.nfc = nfc;
        this.cincog = cincog;
        this.estado = estado;
        this.precioiva = precioiva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(int bluetooth) {
        this.bluetooth = bluetooth;
    }

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getNfc() {
        return nfc;
    }

    public void setNfc(int nfc) {
        this.nfc = nfc;
    }

    public int getCincog() {
        return cincog;
    }

    public void setCincog(int cincog) {
        this.cincog = cincog;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getPrecioiva() {
        return precioiva;
    }

    public void setPrecioiva(double precioiva) {
        this.precioiva = precioiva;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", producto='" + producto + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                ", marca=" + marca +
                ", wifi=" + wifi +
                ", bluetooth=" + bluetooth +
                ", nfc=" + nfc +
                ", cincog=" + cincog +
                ", estado=" + estado +
                ", precioiva=" + precioiva +
                '}';
    }
}
