package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.dgt.modelo.enums.TipoCombustible;

/**
 * Características técnicas de un vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristicasTecnicas {
    private Integer cilindrada;
    private Integer potencia;
    private String numeroBastidores;
    private TipoCombustible combustible;
    private String emisiones;
    private Integer plazas;
    private Integer pesoMaximo;
    private String tipoMotor;
}
