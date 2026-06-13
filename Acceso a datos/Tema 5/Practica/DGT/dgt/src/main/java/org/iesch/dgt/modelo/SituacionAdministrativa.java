package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.dgt.modelo.enums.EstadoVehiculo;

import java.time.LocalDateTime;

/**
 * Situación administrativa de un vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SituacionAdministrativa {
    private EstadoVehiculo estado = EstadoVehiculo.ACTIVO;
    private LocalDateTime fechaEstado;
    private String motivoBaja;
    private LocalDateTime fechaBajaTemporal;
    private LocalDateTime fechaFinBajaTemporal;
    private Boolean precintado = false;
    private LocalDateTime fechaPrecinto;
    private String motivoPrecinto;
}
