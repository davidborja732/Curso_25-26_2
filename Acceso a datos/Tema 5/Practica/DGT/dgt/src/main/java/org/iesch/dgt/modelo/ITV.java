package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Información de la ITV de un vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ITV {
    private Boolean enVigor;
    private LocalDateTime fechaUltimaInspeccion;
    private LocalDateTime fechaCaducidad;
    private String resultado;
    private String numeroInforme;
    private String estacionITV;
}
