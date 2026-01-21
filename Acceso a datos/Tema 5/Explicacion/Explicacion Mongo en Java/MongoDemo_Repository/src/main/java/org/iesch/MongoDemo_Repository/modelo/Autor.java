package org.iesch.MongoDemo_Repository.modelo;

import lombok.*;

@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Getter
@Setter
public class Autor {
    private String nombre;
    private String nacionalidad;

    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
}
