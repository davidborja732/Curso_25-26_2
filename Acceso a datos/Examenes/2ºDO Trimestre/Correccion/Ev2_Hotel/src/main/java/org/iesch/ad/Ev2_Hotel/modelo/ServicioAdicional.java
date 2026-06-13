package org.iesch.ad.Ev2_Hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.hotel.modelo.enums.TipoServicio;

import java.time.LocalDateTime;

/**
 * Representa un servicio adicional consumido durante la estancia
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioAdicional {
    private String id;
    private TipoServicio tipoServicio;
    private Integer cantidad;
    private Double importeUnitario;
    private Double importeTotal;
    private LocalDateTime fechaSolicitud;
    private String observaciones;
}

