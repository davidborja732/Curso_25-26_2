import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperatura {
    private LocalDate fecha;       // Día específico
    private LocalTime horatmax;    // Hora de la máxima
    private LocalTime horatmin;    // Hora de la mínima
    private String nombre;         // Estación meteorológica
    private Float tmax;           // Temperatura máxima
    private Float tmin;           // Temperatura mínima

}
