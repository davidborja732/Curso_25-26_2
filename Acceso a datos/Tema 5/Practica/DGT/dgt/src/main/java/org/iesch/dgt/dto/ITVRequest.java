package org.iesch.dgt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para actualización de ITV
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ITVRequest {

    @NotNull(message = "La fecha de inspección es obligatoria")
    private LocalDateTime fechaInspeccion;

    @NotNull(message = "El resultado es obligatorio")
    private String resultado;

    @NotNull(message = "El número de informe es obligatorio")
    private String numeroInforme;

    @NotNull(message = "La estación ITV es obligatoria")
    private String estacionITV;

    private LocalDateTime proximaInspeccion;
}
