package modelo;

import java.util.ArrayList;

public class Empleado {

    private String nombre;
    private ArrayList<String> proyectos;
    private ArrayList<String> habilidades;
    private int edad;
    private double salario;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getProyectos() {
        return proyectos;
    }

    public void setProjectos(ArrayList<String> proyectos) {
        this.proyectos = proyectos;
    }

    public ArrayList<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Empleado() {
    }

    public Empleado(String nombre, ArrayList<String> proyectos, ArrayList<String> habilidades, int edad, double salario) {
        this.nombre = nombre;
        this.proyectos = proyectos;
        this.habilidades = habilidades;
        this.edad = edad;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", projectos=" + proyectos +
                ", habilidades=" + habilidades +
                ", edad=" + edad +
                ", salario=" + salario +
                '}';
    }
}

