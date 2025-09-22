package Ejercicio_1_y_2;

public class Fruta {
    String nombre;
    String color;
    String origen;

    public Fruta() {
    }

    public Fruta(String nombre, String color, String origen) {
        this.nombre = nombre;
        this.color = color;
        this.origen = origen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "Fruta{" +
                "nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", origen='" + origen + '\'' +
                '}';
    }
}
