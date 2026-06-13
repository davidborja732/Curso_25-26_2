package org.iesch.dgt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesch.dgt.dto.BajaDefinitivaRequest;
import org.iesch.dgt.dto.BajaTemporalRequest;
import org.iesch.dgt.dto.TransferenciaRequest;
import org.iesch.dgt.dto.VerificacionTransferenciaResponse;
import org.iesch.dgt.modelo.Vehiculo;
import org.iesch.dgt.modelo.enums.EstadoVehiculo;
import org.iesch.dgt.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para la gestión de vehículos
 */
@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
@Slf4j
public class VehiculoController {

    private final VehiculoService vehiculoService;

    /**
     * Alta de nuevo vehículo
     * POST /api/vehiculos
     */
    @PostMapping
    public ResponseEntity<Vehiculo> altaVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        log.info("POST /api/vehiculos - Alta de vehículo: {}", vehiculo.getMatricula());
        Vehiculo vehiculoCreado = vehiculoService.altaVehiculo(vehiculo);
        return new ResponseEntity<>(vehiculoCreado, HttpStatus.CREATED);
    }

    /**
     * Buscar vehículo por matrícula
     * GET /api/vehiculos/{matricula}
     */
    @GetMapping("/{matricula}")
    public ResponseEntity<Vehiculo> buscarPorMatricula(@PathVariable String matricula) {
        log.info("GET /api/vehiculos/{}", matricula);
        Vehiculo vehiculo = vehiculoService.buscarPorMatricula(matricula);
        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Listar todos los vehículos
     * GET /api/vehiculos
     */
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listarTodos() {
        log.info("GET /api/vehiculos");
        List<Vehiculo> vehiculos = vehiculoService.listarTodos();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Buscar vehículos por DNI del titular
     * GET /api/vehiculos/titular/{dni}
     */
    @GetMapping("/titular/{dni}")
    public ResponseEntity<List<Vehiculo>> buscarPorTitular(@PathVariable String dni) {
        log.info("GET /api/vehiculos/titular/{}", dni);
        List<Vehiculo> vehiculos = vehiculoService.buscarPorTitular(dni);
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Buscar vehículos por estado
     * GET /api/vehiculos/estado/{estado}
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Vehiculo>> buscarPorEstado(@PathVariable EstadoVehiculo estado) {
        log.info("GET /api/vehiculos/estado/{}", estado);
        List<Vehiculo> vehiculos = vehiculoService.buscarPorEstado(estado);
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Buscar vehículos con ITV caducada
     * GET /api/vehiculos/itv/caducada
     */
    @GetMapping("/itv/caducada")
    public ResponseEntity<List<Vehiculo>> buscarConITVCaducada() {
        log.info("GET /api/vehiculos/itv/caducada");
        List<Vehiculo> vehiculos = vehiculoService.buscarConITVCaducada();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Buscar vehículos con multas pendientes
     * GET /api/vehiculos/multas/pendientes
     */
    @GetMapping("/multas/pendientes")
    public ResponseEntity<List<Vehiculo>> buscarConMultasPendientes() {
        log.info("GET /api/vehiculos/multas/pendientes");
        List<Vehiculo> vehiculos = vehiculoService.buscarConMultasPendientes();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Buscar vehículos transferibles
     * GET /api/vehiculos/transferibles
     */
    @GetMapping("/transferibles")
    public ResponseEntity<List<Vehiculo>> buscarTransferibles() {
        log.info("GET /api/vehiculos/transferibles");
        List<Vehiculo> vehiculos = vehiculoService.buscarVehiculosTransferibles();
        return ResponseEntity.ok(vehiculos);
    }

    /**
     * Baja definitiva de vehículo
     * PUT /api/vehiculos/{matricula}/baja-definitiva
     */
    @PutMapping("/{matricula}/baja-definitiva")
    public ResponseEntity<Vehiculo> bajaDefinitiva(
            @PathVariable String matricula,
            @Valid @RequestBody BajaDefinitivaRequest request) {

        log.info("PUT /api/vehiculos/{}/baja-definitiva", matricula);

        Vehiculo vehiculo = vehiculoService.bajaDefinitiva(
            matricula,
            request.getMotivo(),
            request.getCertificadoDesguace()
        );

        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Baja temporal de vehículo
     * PUT /api/vehiculos/{matricula}/baja-temporal
     */
    @PutMapping("/{matricula}/baja-temporal")
    public ResponseEntity<Vehiculo> bajaTemporal(
            @PathVariable String matricula,
            @Valid @RequestBody BajaTemporalRequest request) {

        log.info("PUT /api/vehiculos/{}/baja-temporal", matricula);

        Vehiculo vehiculo = vehiculoService.bajaTemporal(matricula, request.getMotivo());

        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Reactivar vehículo
     * PUT /api/vehiculos/{matricula}/reactivar
     */
    @PutMapping("/{matricula}/reactivar")
    public ResponseEntity<Vehiculo> reactivar(@PathVariable String matricula) {
        log.info("PUT /api/vehiculos/{}/reactivar", matricula);
        Vehiculo vehiculo = vehiculoService.reactivarVehiculo(matricula);
        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Verificar requisitos para transferencia
     * GET /api/vehiculos/{matricula}/verificar-transferencia
     */
    @GetMapping("/{matricula}/verificar-transferencia")
    public ResponseEntity<VerificacionTransferenciaResponse> verificarTransferencia(
            @PathVariable String matricula) {

        log.info("GET /api/vehiculos/{}/verificar-transferencia", matricula);

        VerificacionTransferenciaResponse response =
            vehiculoService.verificarTransferencia(matricula);

        return ResponseEntity.ok(response);
    }

    /**
     * Transferir vehículo a nuevo titular
     * PUT /api/vehiculos/{matricula}/transferencia
     */
    @PutMapping("/{matricula}/transferencia")
    public ResponseEntity<Map<String, Object>> transferirVehiculo(
            @PathVariable String matricula,
            @Valid @RequestBody TransferenciaRequest request) {

        log.info("PUT /api/vehiculos/{}/transferencia", matricula);

        Vehiculo vehiculo = vehiculoService.transferirVehiculo(matricula, request);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("mensaje", "Transferencia realizada correctamente");
        response.put("vehiculo", vehiculo);
        response.put("advertencia", "El nuevo titular debe abonar el ITMV en 30 días");

        return ResponseEntity.ok(response);
    }
}
