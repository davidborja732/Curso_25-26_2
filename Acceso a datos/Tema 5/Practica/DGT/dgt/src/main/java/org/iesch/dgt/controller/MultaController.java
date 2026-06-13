package org.iesch.dgt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesch.dgt.dto.MultaRequest;
import org.iesch.dgt.dto.PagoMultaRequest;
import org.iesch.dgt.modelo.Multa;
import org.iesch.dgt.modelo.Vehiculo;
import org.iesch.dgt.modelo.enums.EstadoMulta;
import org.iesch.dgt.service.MultaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de multas
 */
@RestController
@RequestMapping("/api/vehiculos/{matricula}/multas")
@RequiredArgsConstructor
@Slf4j
public class MultaController {

    private final MultaService multaService;

    /**
     * Registrar nueva multa
     * POST /api/vehiculos/{matricula}/multas
     */
    @PostMapping
    public ResponseEntity<Vehiculo> registrarMulta(
            @PathVariable String matricula,
            @Valid @RequestBody MultaRequest request) {

        log.info("POST /api/vehiculos/{}/multas", matricula);
        Vehiculo vehiculo = multaService.registrarMulta(matricula, request);
        return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
    }

    /**
     * Listar multas de un vehículo
     * GET /api/vehiculos/{matricula}/multas
     * GET /api/vehiculos/{matricula}/multas?estado=PENDIENTE_PAGO
     */
    @GetMapping
    public ResponseEntity<List<Multa>> listarMultas(
            @PathVariable String matricula,
            @RequestParam(required = false) EstadoMulta estado) {

        log.info("GET /api/vehiculos/{}/multas?estado={}", matricula, estado);

        List<Multa> multas;
        if (estado != null) {
            multas = multaService.listarMultasPorEstado(matricula, estado);
        } else {
            multas = multaService.listarMultas(matricula);
        }

        return ResponseEntity.ok(multas);
    }

    /**
     * Pagar una multa
     * PUT /api/vehiculos/{matricula}/multas/{idMulta}/pagar
     */
    @PutMapping("/{idMulta}/pagar")
    public ResponseEntity<Vehiculo> pagarMulta(
            @PathVariable String matricula,
            @PathVariable String idMulta,
            @Valid @RequestBody PagoMultaRequest request) {

        log.info("PUT /api/vehiculos/{}/multas/{}/pagar", matricula, idMulta);
        Vehiculo vehiculo = multaService.pagarMulta(matricula, idMulta, request);
        return ResponseEntity.ok(vehiculo);
    }
}
