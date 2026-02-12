package org.iesch.ad.Ev2_Hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Representa la factura generada al hacer check-out
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {
    private String numeroFactura;
    private LocalDateTime fecha;
    private Double precioBase;
    private Double serviciosAdicionales;
    private Double suplementos;
    private Double total;
}

