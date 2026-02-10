package org.iesch.dgt.Practica_DGT.modelos;

import org.iesch.dgt.Practica_DGT.modelos.enums.EstadoVehiculo;

import java.time.LocalDateTime;

public class SituacionAdministrativa {
    private EstadoVehiculo estado;
    private LocalDateTime fechaEstado;
    private String motivoBaja;
    private LocalDateTime fechaBajaTemporal;
    private LocalDateTime fechaFinBajaTemporal;
    private boolean precintado;
    private LocalDateTime fechaPrecinto;
    private String motivoPrecinto;
}
