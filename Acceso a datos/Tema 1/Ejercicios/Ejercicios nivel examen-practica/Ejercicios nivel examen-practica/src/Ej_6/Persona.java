package Ej_6;

import java.util.ArrayList;

public class Persona {
    String dni;
    String nombre;
    ArrayList<Coche> coches=new ArrayList<>();

    public Persona() {
    }

    public Persona(String dni, String nombre, ArrayList<Coche> coches) {
        this.dni = dni;
        this.nombre = nombre;
        this.coches = coches;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    public void setCoches(ArrayList<Coche> coches) {
        this.coches = coches;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", coches=" + coches +
                '}';
    }
}
