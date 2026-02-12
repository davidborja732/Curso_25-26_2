package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO respuesta al realizar check-out
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutResponse {
    private String codigo;
    private LocalDateTime horaSalida;
    private FacturaDetalle factura;
    private Integer puntosGanados;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FacturaDetalle {
        private Double precioBase;
        private Double serviciosAdicionales;
        private Double suplementos;
        private Double total;
    }
}

