package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.dgt.modelo.enums.EstadoMulta;

import java.time.LocalDateTime;

/**
 * Multa asociada a un vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Multa {
    private String id;
    private String concepto;
    private Double importe;
    private Integer puntos;
    private LocalDateTime fecha;
    private String lugarInfraccion;
    private String agente;
    private EstadoMulta estado = EstadoMulta.PENDIENTE_PAGO;
    private LocalDateTime fechaPago;
    private String metodoPago;
}
