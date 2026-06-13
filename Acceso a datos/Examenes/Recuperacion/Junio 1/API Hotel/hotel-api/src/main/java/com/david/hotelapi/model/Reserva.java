package com.david.hotelapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("reservas")
public class Reserva {

    @Id
    private String codigo;

    private String dniCliente;
    private String numeroHabitacion;
    private String tipoHabitacion;

    private String fechaEntrada;
    private String fechaSalida;

    private int numeroNoches;
    private int numeroPersonas;

    private String regimen;
    private String temporada;
    private String estado;

    private double precioBase;
    private double precioTotal;

    private List<ServicioAdicional> serviciosAdicionales;

    private String fechaCreacion;
    private String fechaActualizacion;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServicioAdicional {
        private String id;
        private String tipoServicio;
        private int cantidad;
        private double importeUnitario;
        private double importeTotal;
        private String fechaSolicitud;
    }
}
