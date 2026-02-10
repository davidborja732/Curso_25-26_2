package org.iesch.dgt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesch.dgt.dto.MultaRequest;
import org.iesch.dgt.dto.PagoMultaRequest;
import org.iesch.dgt.exception.BusinessException;
import org.iesch.dgt.modelo.Multa;
import org.iesch.dgt.modelo.Vehiculo;
import org.iesch.dgt.modelo.enums.EstadoMulta;
import org.iesch.dgt.repositorio.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Servicio para la gestión de multas
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MultaService {

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoService vehiculoService;

    /**
     * Registrar una nueva multa a un vehículo
     */
    public Vehiculo registrarMulta(String matricula, MultaRequest request) {
        log.info("Registrando multa para vehículo: {}", matricula);

        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        // Validaciones
        if (request.getImporte() <= 0) {
            throw new BusinessException("El importe debe ser mayor que 0");
        }

        if (request.getPuntos() < 0 || request.getPuntos() > 6) {
            throw new BusinessException("Los puntos deben estar entre 0 y 6");
        }

        // Crear multa
        Multa multa = new Multa();
        multa.setId(generarIdMulta());
        multa.setConcepto(request.getConcepto());
        multa.setImporte(request.getImporte());
        multa.setPuntos(request.getPuntos());
        multa.setFecha(request.getFecha() != null ? request.getFecha() : LocalDateTime.now());
        multa.setLugarInfraccion(request.getLugarInfraccion());
        multa.setAgente(request.getAgente());
        multa.setEstado(EstadoMulta.PENDIENTE_PAGO);

        // Añadir multa al vehículo
        vehiculo.getMultas().add(multa);
        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("Multa {} registrada correctamente", multa.getId());

        return vehiculoActualizado;
    }

    /**
     * Pagar una multa
     */
    public Vehiculo pagarMulta(String matricula, String idMulta, PagoMultaRequest request) {
        log.info("Procesando pago de multa {} del vehículo {}", idMulta, matricula);

        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        // Buscar la multa
        Multa multa = vehiculo.getMultas().stream()
            .filter(m -> m.getId().equals(idMulta))
            .findFirst()
            .orElseThrow(() -> new BusinessException("No se encontró la multa con ID: " + idMulta));

        // Validar que esté pendiente de pago
        if (!multa.getEstado().equals(EstadoMulta.PENDIENTE_PAGO)) {
            throw new BusinessException("La multa no está en estado PENDIENTE_PAGO");
        }

        // Registrar el pago
        multa.setEstado(EstadoMulta.PAGADA);
        multa.setFechaPago(LocalDateTime.now());
        multa.setMetodoPago(request.getMetodoPago());

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("Multa {} pagada correctamente", idMulta);

        return vehiculoActualizado;
    }

    /**
     * Listar todas las multas de un vehículo
     */
    public List<Multa> listarMultas(String matricula) {
        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);
        return vehiculo.getMultas();
    }

    /**
     * Listar multas por estado
     */
    public List<Multa> listarMultasPorEstado(String matricula, EstadoMulta estado) {
        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);
        return vehiculo.getMultas().stream()
            .filter(m -> m.getEstado().equals(estado))
            .collect(Collectors.toList());
    }

    /**
     * Generar ID único para multa
     */
    private String generarIdMulta() {
        return "M-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
