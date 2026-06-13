package org.iesch.dgt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesch.dgt.dto.ITVRequest;
import org.iesch.dgt.dto.PagoITMVRequest;
import org.iesch.dgt.modelo.Vehiculo;
import org.iesch.dgt.service.ImpuestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST para la gestión de ITV e impuestos
 */
@RestController
@RequestMapping("/api/vehiculos/{matricula}")
@RequiredArgsConstructor
@Slf4j
public class ImpuestoController {

    private final ImpuestoService impuestoService;

    /**
     * Actualizar ITV
     * PUT /api/vehiculos/{matricula}/itv
     */
    @PutMapping("/itv")
    public ResponseEntity<Vehiculo> actualizarITV(
            @PathVariable String matricula,
            @Valid @RequestBody ITVRequest request) {

        log.info("PUT /api/vehiculos/{}/itv", matricula);
        Vehiculo vehiculo = impuestoService.actualizarITV(matricula, request);
        return ResponseEntity.ok(vehiculo);
    }

    /**
     * Verificar ITV en vigor
     * GET /api/vehiculos/{matricula}/itv/verificar
     */
    @GetMapping("/itv/verificar")
    public ResponseEntity<Map<String, Object>> verificarITV(@PathVariable String matricula) {
        log.info("GET /api/vehiculos/{}/itv/verificar", matricula);

        boolean enVigor = impuestoService.verificarITVEnVigor(matricula);

        Map<String, Object> response = new HashMap<>();
        response.put("matricula", matricula);
        response.put("itvEnVigor", enVigor);

        return ResponseEntity.ok(response);
    }

    /**
     * Registrar pago de ITMV
     * POST /api/vehiculos/{matricula}/impuestos/itmv
     */
    @PostMapping("/impuestos/itmv")
    public ResponseEntity<Vehiculo> registrarPagoITMV(
            @PathVariable String matricula,
            @Valid @RequestBody PagoITMVRequest request) {

        log.info("POST /api/vehiculos/{}/impuestos/itmv", matricula);
        Vehiculo vehiculo = impuestoService.registrarPagoITMV(matricula, request);
        return ResponseEntity.ok(vehiculo);
    }
}
