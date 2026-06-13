package com.david.hotelapi.controller;

import com.david.hotelapi.model.Reserva;
import com.david.hotelapi.model.dto.ReservaRequest;
import com.david.hotelapi.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    // GET /reservas
    @GetMapping
    public ResponseEntity<List<Reserva>> getAll() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    // GET /reservas/{codigo}
    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getByCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(reservaService.findByCodigo(codigo));
    }

    // POST /reservas
    @PostMapping
    public ResponseEntity<Reserva> create(@Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.ok(reservaService.create(request));
    }

    // DELETE /reservas/{codigo}
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> delete(@PathVariable String codigo) {
        reservaService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}
