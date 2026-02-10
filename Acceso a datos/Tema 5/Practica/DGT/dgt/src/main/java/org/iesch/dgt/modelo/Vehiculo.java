package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.dgt.modelo.enums.TipoVehiculo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad principal que representa un vehículo en el sistema DGT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehiculos")
public class Vehiculo {

    @Id
    private String id;

    @Indexed(unique = true)
    private String matricula;

    @Indexed(unique = true)
    private String bastidor;

    private String marca;
    private String modelo;
    private String color;
    private TipoVehiculo tipoVehiculo;
    private Integer anioFabricacion;
    private LocalDateTime fechaPrimeraMatriculacion;

    // Documentos embebidos
    private CaracteristicasTecnicas caracteristicasTecnicas;
    private Titular titular;
    private SituacionAdministrativa situacionAdministrativa;
    private ITV itv;
    private Impuesto impuestos;

    // Arrays embebidos
    private List<Multa> multas = new ArrayList<>();
    private List<HistorialTitular> historialTitulares = new ArrayList<>();

    // Auditoría
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
