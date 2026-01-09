package org.iesch.examendi;

import java.time.LocalDate;

public class Empleados {
    int id;
    String nombre;
    LocalDate fechaNac;
    String sexo;
    String departamento;
    int salario;
    Double salarioneto;
    private double salarioNeto;
    public double getSalarioNeto() { return salarioNeto; }
    public void setSalarioNeto(double salarioNeto) { this.salarioNeto = salarioNeto; }


    public Empleados(int id, Double salarioneto, int salario, String departamento, LocalDate fechaNac, String nombre, String sexo) {
        this.id = id;
        this.salarioneto = salarioneto;
        this.salario = salario;
        this.departamento = departamento;
        this.fechaNac = fechaNac;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public Empleados() {
    }

    public Empleados(int id, String nombre, LocalDate fechaNac, String sexo, String departamento, int salario) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.departamento = departamento;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public Double getSalarioneto() {
        return salarioneto;
    }

    public void setSalarioneto(Double salarioneto) {
        this.salarioneto = salarioneto;
    }

    @Override
    public String toString() {
        return "Empleados{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaNac=" + fechaNac +
                ", sexo=" + sexo +
                ", departamento='" + departamento + '\'' +
                ", salario=" + salario +
                '}';
    }
}
