package modelo;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@Builder
public class Persona {
    private String nombre;
    private String apellido;
}
