package org.iesch.ad.Ev2_Hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una dirección postal
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    private String calle;
    private String numero;
    private String codigoPostal;
    private String ciudad;
    private String pais;
}

