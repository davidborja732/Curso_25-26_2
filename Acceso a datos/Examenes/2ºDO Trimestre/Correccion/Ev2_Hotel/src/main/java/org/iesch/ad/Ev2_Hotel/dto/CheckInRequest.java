package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.ad.Ev2_Hotel.modelo.enums.MetodoPago;

/**
 * DTO para realizar check-in
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInRequest {
    private MetodoPago metodoPago;
    private String observaciones;
}

