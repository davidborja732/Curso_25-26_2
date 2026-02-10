package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domicilio fiscal de un titular
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Domicilio {
    private String calle;
    private String numero;
    private String piso;
    private String puerta;
    private String codigoPostal;
    private String localidad;
    private String provincia;
}
