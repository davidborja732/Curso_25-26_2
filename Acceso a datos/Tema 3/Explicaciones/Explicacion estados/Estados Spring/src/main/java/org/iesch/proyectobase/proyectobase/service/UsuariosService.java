package org.iesch.proyectobase.proyectobase.service;

import org.iesch.proyectobase.proyectobase.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class UsuariosService {
    @Autowired
    Map<Long, Usuario> personas;
    public Usuario anadepersona(Usuario usuario){
        Long maximo= Collections.max(personas.keySet());
        usuario.setId(maximo);
        personas.put(maximo+1, usuario);
        return usuario;
    }
}
