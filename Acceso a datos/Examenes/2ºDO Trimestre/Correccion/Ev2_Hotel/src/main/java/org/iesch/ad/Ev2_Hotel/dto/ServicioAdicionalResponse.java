package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO respuesta al solicitar servicio adicional
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioAdicionalResponse {
    private String servicioId;
    private Double importe;
    private Double nuevoTotal;
}

