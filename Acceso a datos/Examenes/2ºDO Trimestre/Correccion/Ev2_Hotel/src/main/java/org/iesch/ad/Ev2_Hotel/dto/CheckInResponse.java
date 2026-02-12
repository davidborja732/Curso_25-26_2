package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO respuesta al realizar check-in
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInResponse {
    private String codigo;
    private String estado;
    private LocalDateTime horaEntrada;
    private Double suplementoEarlyCheckIn;
    private Double precioFinal;
}

