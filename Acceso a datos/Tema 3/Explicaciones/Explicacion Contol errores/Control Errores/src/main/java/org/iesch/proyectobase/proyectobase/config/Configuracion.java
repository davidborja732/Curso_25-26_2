package org.iesch.proyectobase.proyectobase.config;

import org.iesch.proyectobase.proyectobase.modelo.Estudiante;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Configuracion {
    @Bean
    public Map<Long, Estudiante> inicializa(){
        Map<Long,Estudiante> estudiantes=new HashMap<>();
        estudiantes.put(1L,new Estudiante(1,"David","Borja","dborjam@iesch.org",21,"DAM2"));
        estudiantes.put(2L,new Estudiante(2,"Pepe","Lopez","lpepe@iesch.org",19,"DAM1"));
        estudiantes.put(3L,new Estudiante(3,"Nicolas","Alvarez","nalvarez@iesch.org",23,"DAW"));
        return estudiantes;
    }

}
