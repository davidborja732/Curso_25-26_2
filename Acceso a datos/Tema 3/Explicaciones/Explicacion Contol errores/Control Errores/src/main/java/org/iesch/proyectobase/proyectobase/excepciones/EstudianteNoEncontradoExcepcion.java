package org.iesch.proyectobase.proyectobase.excepciones;

public class EstudianteNoEncontradoExcepcion extends RuntimeException{
    public EstudianteNoEncontradoExcepcion(Long id){
        super("No se encontro el estudiante "+id);
    }
    public EstudianteNoEncontradoExcepcion(String dato){
        super("No se encontro alumnos con el ciclo "+dato);
    }
}
