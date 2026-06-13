package org.iesch.ej_11;

public class Genero {
    int id;
    String Nombre;

    public Genero() {
    }

    public Genero(int id, String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                '}';
    }
}
