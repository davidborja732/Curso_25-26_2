package org.iesch.ad.Ev2_Hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.ad.Ev2_Hotel.modelo.enums.TipoServicio;


/**
 * DTO para solicitar un servicio adicional
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioAdicionalRequest {
    private TipoServicio tipoServicio;
    private Integer cantidad;
    private String observaciones;
}

