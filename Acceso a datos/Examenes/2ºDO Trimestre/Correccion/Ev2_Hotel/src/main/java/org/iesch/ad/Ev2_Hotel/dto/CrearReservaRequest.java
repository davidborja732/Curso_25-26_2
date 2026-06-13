package org.iesch.ad.Ev2_Hotel.dto;

import java.time.LocalDateTime;


import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.AllArgsConstructor;
import org.iesch.ad.Ev2_Hotel.modelo.enums.*;


/**
 * DTO para crear una nueva reserva
 */

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
public class CrearReservaRequest {

        private Regimen regimen;
        private Integer numeroPersonas;
        private LocalDateTime fechaSalida;
        private LocalDateTime fechaEntrada;
        private TipoHabitacion tipoHabitacion;
        private String dniCliente;
    }






