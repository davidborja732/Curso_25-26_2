package org.iesch.dgt.Practica_DGT.modelos;

import lombok.Data;

@Data
public class DomicilioFiscal {
    private String calle;
    private String numero;
    private String piso;
    private String puerta;
    private String codigoPostal;
    private String localidad;
    private String provincia;
}
