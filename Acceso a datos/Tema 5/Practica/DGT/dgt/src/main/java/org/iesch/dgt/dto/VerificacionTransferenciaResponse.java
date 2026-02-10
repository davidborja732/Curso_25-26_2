package org.iesch.dgt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para respuesta de verificación de transferencia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificacionTransferenciaResponse {
    private String matricula;
    private boolean puedeTransferir;
    private boolean itmvPagado;
    private boolean sinMultasPendientes;
    private boolean vehiculoActivo;
    private boolean itvEnVigor;
    private List<String> impedimentos;
}
