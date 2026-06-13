package org.iesch.datos_entre_ventanas;

public class Persona {
    String nombre;
    String apellidos;
    String localidad;
    int salario;

    public Persona() {
    }

    public Persona(String nombre, String apellidos, String localidad, int salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }
}
