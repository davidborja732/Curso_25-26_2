package org.iesch.ad.Ev2_Hotel.service;

import org.iesch.ad.Ev2_Hotel.dto.CrearReservaRequest;
import org.iesch.ad.Ev2_Hotel.dto.CrearReservaResponse;
import org.iesch.ad.Ev2_Hotel.modelo.Cliente;
import org.iesch.ad.Ev2_Hotel.modelo.Habitacion;
import org.iesch.ad.Ev2_Hotel.modelo.Reserva;
import org.iesch.ad.Ev2_Hotel.modelo.enums.EstadoHabitacion;
import org.iesch.ad.Ev2_Hotel.modelo.enums.EstadoReserva;
import org.iesch.ad.Ev2_Hotel.modelo.enums.Temporada;
import org.iesch.ad.Ev2_Hotel.modelo.enums.TipoHabitacion;
import org.iesch.ad.Ev2_Hotel.repository.ClienteRepository;
import org.iesch.ad.Ev2_Hotel.repository.HabitacionRepository;
import org.iesch.ad.Ev2_Hotel.repository.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ReservaService {
    @Autowired
    HabitacionRepository habitacionRepositorio;
    @Autowired
    ReservasRepository reservasRepositorio;

    public CrearReservaResponse crearReserva(CrearReservaRequest request) {
        Cliente cliente = ClienteRepository.findByDNI(request.getDniCliente());
        // Calcular numero de noches
        long numeroNoches = request.getFechaEntrada().toLocalDate().until(request.getFechaSalida().toLocalDate()).getDays();
        // Determinar Tipo Habitacion(Subida a vip en caso de cumplir condicion)
        TipoHabitacion tipoSolicitado = request.getTipoHabitacion();
        TipoHabitacion tipoAsignado = tipoSolicitado;
        /*if (cliente.getEsVIP() && cliente.getTotalReservas()>5){
            tipoAsignado=
        }*/
        // Buscar si hay una habitacion de ese tipo libre
        List<Habitacion> habitacionesDisponibles = habitacionRepositorio.findByTipoHabitacionAndEstado(tipoAsignado, EstadoHabitacion.DISPONIBLE);
        if (habitacionesDisponibles.isEmpty()) {
            throw new RuntimeException("No hay habitaciones libres");
        }
        //Vemos que la habitacion no esta reservada en esas fechas
        Habitacion habitacionAsignada = null;
        for (Habitacion hab : habitacionesDisponibles) {
            if (!tieneReservasEnFechas(hab.getNumero(), request.getFechaEntrada(), request.getFechaSalida())) {
                habitacionAsignada = hab;
                break;
            }
        }
        Temporada temporada determinarTemporada(request.getFechaEntrada());
        // Calcular precio base
        double precioBaseNoche=habitacionAsignada.getPrecioBaseNoche();
        double precioBase=precioBaseNoche*numeroNoches;
        //Aplicar el modificador de temporada
        double multiplicadorTemporada=switch (temporada){
            
        }
        return null;


    }
    private Temporada determinarTemporada(LocalDateTime fechaEntrada){
        int mes=fechaEntrada.getMonthValue();
        // 3 a 8 alta
        if (mes>=3&&mes<=8){
            return Temporada.ALTA;
        }else
        // 9 a 11 alta
        if (mes>=3&&mes<=8){
            return Temporada.MEDIA;
        }
        else {
            return Temporada.BAJA;
        }
    }
    private boolean tieneReservasEnFechas(String numero, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        List<EstadoReserva> estadoActivos = Arrays.asList(EstadoReserva.CONFIRMADA, EstadoReserva.EN_CURSO);
        List<Reserva> reservasHabitacion = reservasRepositorio.findByNumeroHabitacionAndEstadoIn(numero, estadoActivos);
        //Vemos si las fechas coinciden
        for (Reserva r : reservasHabitacion) {
            boolean solapamiento = !(fechaSalida.isBefore(r.getFechaEntrada()) || fechaEntrada.isAfter(r.getFechaSalida()));
            if (solapamiento) {
                return true;
            }
        }
        return false;
    }

}


