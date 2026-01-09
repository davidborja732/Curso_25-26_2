package org.iesch.examendi;

public class Productotabla {
    int id;
    String producto;
    double precio;
    String categoria;
    String marca;
    String wifi;
    String bluetooth;
    String nfc;
    String cincog;
    String estado;
    private double precioiva;

    public Productotabla() {
    }

    public Productotabla(int id, String producto, double precio, String categoria, String marca, String wifi, String bluetooth, String nfc, String cincog, String estado, double precioiva) {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getWifi() {
        return wifi;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public String getCincog() {
        return cincog;
    }

    public void setCincog(String cincog) {
        this.cincog = cincog;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioiva() {
        return precioiva;
    }

    public void setPrecioiva(double precioiva) {
        this.precioiva = precioiva;
    }
}
