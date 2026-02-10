package org.iesch.dgt.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.dgt.modelo.Titular;

/**
 * DTO para solicitud de transferencia de vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaRequest {

    @Valid
    @NotNull(message = "El nuevo titular es obligatorio")
    private Titular nuevoTitular;

    @NotBlank(message = "El motivo de transferencia es obligatorio")
    private String motivoTransferencia;

    private Double precioVenta;
}
