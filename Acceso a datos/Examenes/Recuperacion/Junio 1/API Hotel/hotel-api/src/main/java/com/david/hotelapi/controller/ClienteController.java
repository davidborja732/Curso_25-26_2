package com.david.hotelapi.controller;

import com.david.hotelapi.model.Cliente;
import com.david.hotelapi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Cliente> getByDni(@PathVariable String dni) {
        return ResponseEntity.ok(clienteService.findByDni(dni));
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.create(cliente));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable String dni) {
        clienteService.delete(dni);
        return ResponseEntity.noContent().build();
    }
}
