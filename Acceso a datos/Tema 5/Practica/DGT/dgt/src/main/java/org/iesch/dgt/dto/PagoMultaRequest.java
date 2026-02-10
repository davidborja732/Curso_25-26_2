package org.iesch.dgt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para pago de multa
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoMultaRequest {

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    private String numeroTransaccion;
}
