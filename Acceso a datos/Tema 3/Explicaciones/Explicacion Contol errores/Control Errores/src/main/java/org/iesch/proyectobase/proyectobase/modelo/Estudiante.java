package org.iesch.proyectobase.proyectobase.modelo;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private long id;
    private String nombre;
    private String apellidos;
    private String email;
    private Integer edad;
    private String ciclo;// DAM,DAW,ETC....
}
