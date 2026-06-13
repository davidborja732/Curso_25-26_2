package org.iesch.dgt.Practica_DGT.modelos;

import lombok.Data;
import org.iesch.dgt.Practica_DGT.modelos.enums.TipoCombustible;

@Data
public class CaracteristicasTecnicas {
    private  int cilindrada;
    private int potencia;
    private String numeroBastidores;
    private TipoCombustible tipoCombustible;
    private String emisiones;
    private int plazas;
    private int pesoMaximo;
    private String tipoMotor;
}
