package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Registro histórico de titulares de un vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialTitular {
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String motivoTransferencia;
}
