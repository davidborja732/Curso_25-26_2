package com.david.hotelapi.service;

import com.david.hotelapi.exception.BadRequestException;
import com.david.hotelapi.exception.ConflictException;
import com.david.hotelapi.exception.NotFoundException;
import com.david.hotelapi.model.Reserva;
import com.david.hotelapi.model.Reserva.ServicioAdicional;
import com.david.hotelapi.model.dto.ReservaRequest;
import com.david.hotelapi.repository.ClienteRepository;
import com.david.hotelapi.repository.HabitacionRepository;
import com.david.hotelapi.repository.ReservaRepository;
import com.david.hotelapi.repository.ServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final HabitacionRepository habitacionRepository;
    private final ServicioRepository servicioRepository;

    // LISTAR TODAS
    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    // BUSCAR POR CÓDIGO
    public Reserva findByCodigo(String codigo) {
        return reservaRepository.findById(codigo)
                .orElseThrow(() -> new NotFoundException("La reserva no existe"));
    }

    // CREAR RESERVA
    public Reserva create(ReservaRequest request) {

        // 1. Validar cliente
        if (!clienteRepository.existsById(request.getDniCliente())) {
            throw new NotFoundException("El cliente no existe");
        }

        // 2. Validar habitación
        var habitacion = habitacionRepository.findById(request.getNumeroHabitacion())
                .orElseThrow(() -> new NotFoundException("La habitación no existe"));

        // 3. Validar fechas
        LocalDate entrada = LocalDate.parse(request.getFechaEntrada());
        LocalDate salida = LocalDate.parse(request.getFechaSalida());

        if (!salida.isAfter(entrada)) {
            throw new BadRequestException("La fecha de salida debe ser posterior a la de entrada");
        }

        // 4. Comprobar solapamientos
        boolean solapa = reservaRepository
                .existsByNumeroHabitacionAndFechaEntradaLessThanEqualAndFechaSalidaGreaterThanEqual(
                        request.getNumeroHabitacion(),
                        request.getFechaSalida(),
                        request.getFechaEntrada()
                );

        if (solapa) {
            throw new ConflictException("La habitación ya está reservada en esas fechas");
        }

        // 5. Calcular noches
        int noches = (int) ChronoUnit.DAYS.between(entrada, salida);

        // 6. Calcular precio base
        double precioBase = noches * habitacion.getPrecioNoche();

        // 7. Procesar servicios adicionales
        List<ServicioAdicional> servicios = request.getServiciosAdicionales() == null
                ? List.of()
                : request.getServiciosAdicionales().stream().map(s -> {

            var servicio = servicioRepository.findById(s.getId())
                    .orElseThrow(() -> new NotFoundException("El servicio " + s.getId() + " no existe"));

            double total = servicio.getPrecioUnitario() * s.getCantidad();

            return ServicioAdicional.builder()
                    .id(s.getId())
                    .tipoServicio(servicio.getTipoServicio())
                    .cantidad(s.getCantidad())
                    .importeUnitario(servicio.getPrecioUnitario())
                    .importeTotal(total)
                    .fechaSolicitud(LocalDate.now().toString())
                    .build();
        }).toList();

        // 8. Calcular precio total
        double precioServicios = servicios.stream()
                .mapToDouble(ServicioAdicional::getImporteTotal)
                .sum();

        double precioTotal = precioBase + precioServicios;

        // 9. Crear reserva
        Reserva reserva = Reserva.builder()
                .codigo("RSV-" + System.currentTimeMillis())
                .dniCliente(request.getDniCliente())
                .numeroHabitacion(request.getNumeroHabitacion())
                .tipoHabitacion(habitacion.getTipo())
                .fechaEntrada(request.getFechaEntrada())
                .fechaSalida(request.getFechaSalida())
                .numeroNoches(noches)
                .numeroPersonas(request.getNumeroPersonas())
                .regimen(request.getRegimen())
                .temporada(request.getTemporada())
                .estado("ACTIVA")
                .precioBase(precioBase)
                .precioTotal(precioTotal)
                .serviciosAdicionales(servicios)
                .fechaCreacion(LocalDate.now().toString())
                .fechaActualizacion(LocalDate.now().toString())
                .build();

        return reservaRepository.save(reserva);
    }

    // ELIMINAR
    public void delete(String codigo) {
        if (!reservaRepository.existsById(codigo)) {
            throw new NotFoundException("La reserva no existe");
        }
        reservaRepository.deleteById(codigo);
    }
}
