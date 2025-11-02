package org.iesch.proyectobase.proyectobase.modelo.dto;
import lombok.*;
import org.iesch.proyectobase.proyectobase.modelo.Estudiante;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteResponseDTO {
    private long id;
    private String nombre;
    private String apellidos;
    public  EstudianteResponseDTO(Estudiante estudiante){
        this.id= estudiante.getId();
        this.nombre= estudiante.getNombre();
        this.apellidos= estudiante.getApellidos();
    }
}
