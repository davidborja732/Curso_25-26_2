package org.iesch.proyectobase.proyectobase.controller;

import org.iesch.proyectobase.proyectobase.modelo.Persona;
import org.iesch.proyectobase.proyectobase.service.PersonasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UsuarioController {
    @Autowired
    Map<Long,Persona> personas;
    @Autowired
    PersonasService personasService;
    @PostMapping("/usuario")
    public ResponseEntity<?> registra (@RequestBody Persona persona){

    }
}
