package org.iesch.proyectobase.proyectobase.modelo;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Producto {
    long id;
    String name;
    double price;
    String descripcion;
    String categoria;
    int stock;

    /*@Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }*/
}
