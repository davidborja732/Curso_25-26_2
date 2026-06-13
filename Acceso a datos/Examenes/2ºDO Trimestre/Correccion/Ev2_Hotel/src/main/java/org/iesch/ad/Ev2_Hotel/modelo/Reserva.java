package org.iesch.ad.Ev2_Hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.ad.Ev2_Hotel.modelo.enums.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una reserva de habitación
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;

    @Indexed(unique = true)
    private String codigo;

    private String dniCliente;
    private String numeroHabitacion;
    private TipoHabitacion tipoHabitacion;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;
    private Integer numeroNoches;
    private Integer numeroPersonas;
    private Regimen regimen;
    private Temporada temporada;
    private EstadoReserva estado;
    private Double precioBase;
    private Double descuentoAplicado;
    private Double precioTotal;
    private MetodoPago metodoPago;
    private LocalDateTime horaEntradaReal;
    private LocalDateTime horaSalidaReal;
    private List<Suplemento> suplementos = new ArrayList<>();
    private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
    private String observaciones;
    private Factura factura;

    // Para cancelaciones
    private LocalDateTime fechaCancelacion;
    private String motivoCancelacion;
    private Double importeReembolso;
    private Integer porcentajeReembolso;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}

