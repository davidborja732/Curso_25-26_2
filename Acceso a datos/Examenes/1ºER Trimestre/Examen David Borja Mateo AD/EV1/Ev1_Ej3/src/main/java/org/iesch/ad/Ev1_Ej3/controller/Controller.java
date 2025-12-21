package org.iesch.ad.Ev1_Ej3.controller;

import org.iesch.ad.Ev1_Ej3.repository.ClienteRepository;
import org.iesch.ad.Ev1_Ej3.repository.LibrosRepository;
import org.iesch.ad.Ev1_Ej3.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    LibrosRepository librosRepository;
    @GetMapping("/clientes")
    public ResponseEntity<?> mostrartodos(){
        return ResponseEntity.ok(clienteRepository.findAll());
    }
    @GetMapping("/api/clientesPorFecha/{fecha}")
    public ResponseEntity<?> fechaespecifica(@PathVariable String fecha){
        return ResponseEntity.ok(pedidoRepository.findByFecha(fecha));
    }
    @GetMapping("/api/librosPorEditorial/{editorial}")
    public ResponseEntity<?> libroeditorial(@PathVariable String editorial){
        return ResponseEntity.ok(librosRepository.findByEditorial(editorial));
    }

}
