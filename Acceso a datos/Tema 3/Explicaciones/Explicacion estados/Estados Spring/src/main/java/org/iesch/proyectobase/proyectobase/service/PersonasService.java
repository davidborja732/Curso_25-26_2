package org.iesch.proyectobase.proyectobase.service;

import org.iesch.proyectobase.proyectobase.modelo.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class PersonasService {
    @Autowired
    Map<Long, Persona> personas;
    public Persona anadepersona(Persona persona){
        Long maximo= Collections.max(personas.keySet());
        persona.setId(maximo);
        personas.put(maximo+1,persona);
        return persona;
    }
}
