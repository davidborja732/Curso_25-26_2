package org.iesch.dgt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para solicitud de baja temporal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BajaTemporalRequest {

    @NotBlank(message = "El motivo es obligatorio")
    private String motivo;

    private LocalDateTime fechaInicio;
}
