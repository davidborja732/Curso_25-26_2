package org.iesch.proyectobase.proyectobase.modelo;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Persona {
    long id;
    String nombre;
    String apellido;
    String direccion;
    String password;
}
