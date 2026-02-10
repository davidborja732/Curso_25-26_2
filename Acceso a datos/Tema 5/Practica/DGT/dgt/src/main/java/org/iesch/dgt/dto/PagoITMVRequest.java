package org.iesch.dgt.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para pago de ITMV
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoITMVRequest {

    @NotNull(message = "El año es obligatorio")
    private Integer anio;

    @NotNull(message = "El importe es obligatorio")
    @Min(value = 0, message = "El importe debe ser mayor que 0")
    private Double importe;

    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDateTime fechaPago;

    private String justificante;
}
