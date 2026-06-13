package org.iesch.proyectobase.proyectobase.controller;

import org.iesch.proyectobase.proyectobase.modelo.Usuario;
import org.iesch.proyectobase.proyectobase.modelo.UsuarioDTOPeticion;
import org.iesch.proyectobase.proyectobase.modelo.UsuarioDTORespuesta;
import org.iesch.proyectobase.proyectobase.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@RestController
public class UsuarioController {
    @Autowired
    Map<Long, Usuario> personas;
    @Autowired
    UsuariosService usuariosService;
    @PostMapping("/usuario")
    public ResponseEntity<?> registra (@RequestBody UsuarioDTOPeticion usuarioDTOPeticion){
        Usuario usuario= Usuario.builder().nombre(usuarioDTOPeticion.getNombre()).password(usuarioDTOPeticion.getPassword()).build();
        Usuario usuario1= usuariosService.anadepersona(usuario);
        URI location=URI.create("/usuario/"+usuario1.getId());
        UsuarioDTORespuesta usuarioDTORespuesta=UsuarioDTORespuesta.builder().nombre(usuario1.getNombre()).build();
        return ResponseEntity.created(location).body(usuarioDTORespuesta);
    }
}
