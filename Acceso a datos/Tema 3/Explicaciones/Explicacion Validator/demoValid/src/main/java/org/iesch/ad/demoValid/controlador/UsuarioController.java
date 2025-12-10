package org.iesch.ad.demoValid.controlador;

import jakarta.validation.Valid;
import org.iesch.ad.demoValid.modelo.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @PostMapping
    public ResponseEntity<String> crearusuario(@Valid @RequestBody UsuarioDTO usuario){
        System.out.println(usuario);
        return ResponseEntity.ok(usuario.toString());
    }
}
