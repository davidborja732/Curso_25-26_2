package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO respuesta al cancelar una reserva
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelarReservaResponse {
    private String codigo;
    private String estado;
    private Double importeReembolso;
    private Integer porcentajeReembolso;
}

