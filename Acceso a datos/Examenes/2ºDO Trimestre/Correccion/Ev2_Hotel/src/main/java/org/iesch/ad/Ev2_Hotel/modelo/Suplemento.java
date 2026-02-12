package org.iesch.ad.Ev2_Hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un suplemento aplicado a la reserva
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suplemento {
    private String tipo;  // "EARLY_CHECK_IN", "LATE_CHECK_OUT"
    private Double importe;
}

