package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Información de impuestos del vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Impuesto {
    private Boolean itmvPagado;
    private LocalDateTime fechaUltimoPagoITMV;
    private Double importeITMV;
    private Integer anioITMV;
}
