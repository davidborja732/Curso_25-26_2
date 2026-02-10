package org.iesch.dgt.Practica_DGT.modelos;

import lombok.Data;
import org.iesch.dgt.Practica_DGT.modelos.enums.TipoVehiculo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "dgt")
public class Vehiculo {
    @Id
    private String id;
    private String matricula;
    private String bastidor;
    private String marca;
    private String modelo;
    private String color;
    private TipoVehiculo tipoVehiculo;
    private int anioFabricacion;
    private LocalDateTime fechaPrimeraMatriculacion;

    private CaracteristicasTecnicas caracteristicasTecnicas;
    private Titular titular;
    private SituacionAdministrativa situacionAdministrativa;
    private Itv itv;
    private List<Multa> multas;
    private Impuestos impuestos;
    private List<HistorialTitular> historialTitulares;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
