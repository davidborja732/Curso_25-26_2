package org.iesch.dgt.Practica_DGT.modelos;

import lombok.Data;
import org.iesch.dgt.Practica_DGT.modelos.enums.EstadoMulta;

import java.time.LocalDateTime;

@Data
public class Multa {
    private String id;
    private String concepto;
    private double importe;
    private int puntos;
    private LocalDateTime fecha;
    private String LugarInfreccion;
    private String agente;
    private EstadoMulta estado;
    private LocalDateTime fechaPago;
    private String metodoPago;
}
