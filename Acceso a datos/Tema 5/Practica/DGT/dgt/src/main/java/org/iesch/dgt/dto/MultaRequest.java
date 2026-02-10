package org.iesch.dgt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para registro de multa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultaRequest {

    @NotBlank(message = "El concepto es obligatorio")
    private String concepto;

    @NotNull(message = "El importe es obligatorio")
    @Min(value = 0, message = "El importe debe ser mayor que 0")
    private Double importe;

    @NotNull(message = "Los puntos son obligatorios")
    @Min(value = 0, message = "Los puntos deben ser 0 o más")
    private Integer puntos;

    private LocalDateTime fecha;

    @NotBlank(message = "El lugar de infracción es obligatorio")
    private String lugarInfraccion;

    @NotBlank(message = "El agente es obligatorio")
    private String agente;
}
