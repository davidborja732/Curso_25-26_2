package org.iesch.proyectobase.proyectobase.excepciones;

public class DatosInvalidosException extends RuntimeException{
    public DatosInvalidosException(String cicloNoValido) {
        super(cicloNoValido);
    }
}
