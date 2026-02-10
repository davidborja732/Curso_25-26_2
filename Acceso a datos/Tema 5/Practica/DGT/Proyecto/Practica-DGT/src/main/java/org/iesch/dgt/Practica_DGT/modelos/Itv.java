package org.iesch.dgt.Practica_DGT.modelos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Itv {
    private boolean enVigor;
    private LocalDateTime fechaUltimaInspeccion;
    private LocalDateTime fechaCaducidad;
    private String resultado;
    private String numeroInforme;
    private String estacionITV;
}
