package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO respuesta al crear una reserva
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearReservaResponse {
    private String codigo;
    private String estado;
    private Double precioTotal;
    private Double descuentoAplicado;
    private Integer numeroNoches;
    private String habitacionAsignada;
}

