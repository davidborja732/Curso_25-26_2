package org.iesch.dgt.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesch.dgt.dto.ITVRequest;
import org.iesch.dgt.dto.PagoITMVRequest;
import org.iesch.dgt.exception.BusinessException;
import org.iesch.dgt.modelo.ITV;
import org.iesch.dgt.modelo.Impuesto;
import org.iesch.dgt.modelo.Vehiculo;
import org.iesch.dgt.repositorio.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;

/**
 * Servicio para la gestión de ITV e impuestos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImpuestoService {

    private final VehiculoRepository vehiculoRepository;
    private final VehiculoService vehiculoService;

    /**
     * Actualizar ITV de un vehículo
     */
    public Vehiculo actualizarITV(String matricula, ITVRequest request) {
        log.info("Actualizando ITV del vehículo: {}", matricula);

        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        // Validar fecha de inspección no futura
        if (request.getFechaInspeccion().isAfter(LocalDateTime.now())) {
            throw new BusinessException("La fecha de inspección no puede ser futura");
        }

        ITV itv = vehiculo.getItv();
        if (itv == null) {
            itv = new ITV();
            vehiculo.setItv(itv);
        }

        itv.setFechaUltimaInspeccion(request.getFechaInspeccion());
        itv.setResultado(request.getResultado());
        itv.setNumeroInforme(request.getNumeroInforme());
        itv.setEstacionITV(request.getEstacionITV());

        // Actualizar estado según resultado
        if ("FAVORABLE".equals(request.getResultado())) {
            itv.setEnVigor(true);
            itv.setFechaCaducidad(request.getProximaInspeccion());
        } else {
            itv.setEnVigor(false);
        }

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("ITV actualizada correctamente");

        return vehiculoActualizado;
    }

    /**
     * Verificar ITV en vigor
     */
    public boolean verificarITVEnVigor(String matricula) {
        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        if (vehiculo.getItv() == null) {
            return false;
        }

        return vehiculo.getItv().getEnVigor() &&
               vehiculo.getItv().getFechaCaducidad().isAfter(LocalDateTime.now());
    }

    /**
     * Registrar pago de ITMV
     */
    public Vehiculo registrarPagoITMV(String matricula, PagoITMVRequest request) {
        log.info("Registrando pago de ITMV para vehículo: {}", matricula);

        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);

        // Validar año no futuro
        int anioActual = Year.now().getValue();
        if (request.getAnio() > anioActual) {
            throw new BusinessException("No se puede pagar el ITMV de un año futuro");
        }

        // Validar que no se pague el mismo año dos veces
        if (vehiculo.getImpuestos() != null &&
            vehiculo.getImpuestos().getAnioITMV() != null &&
            vehiculo.getImpuestos().getAnioITMV().equals(request.getAnio()) &&
            vehiculo.getImpuestos().getItmvPagado()) {
            throw new BusinessException("El ITMV del año " + request.getAnio() + " ya ha sido pagado");
        }

        Impuesto impuesto = vehiculo.getImpuestos();
        if (impuesto == null) {
            impuesto = new Impuesto();
            vehiculo.setImpuestos(impuesto);
        }

        impuesto.setItmvPagado(true);
        impuesto.setFechaUltimoPagoITMV(request.getFechaPago());
        impuesto.setImporteITMV(request.getImporte());
        impuesto.setAnioITMV(request.getAnio());

        vehiculo.setFechaActualizacion(LocalDateTime.now());

        Vehiculo vehiculoActualizado = vehiculoRepository.save(vehiculo);
        log.info("Pago de ITMV registrado correctamente");

        return vehiculoActualizado;
    }
}
