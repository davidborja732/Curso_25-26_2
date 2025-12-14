package org.iesch.proyectobase.proyectobase.modelo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Usuario {
    long id;
    String nombre;
    String apellido;
    String direccion;
    String password;
}
