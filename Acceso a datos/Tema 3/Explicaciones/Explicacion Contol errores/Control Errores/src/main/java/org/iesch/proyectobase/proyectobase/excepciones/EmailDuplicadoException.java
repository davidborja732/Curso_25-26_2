package org.iesch.proyectobase.proyectobase.excepciones;


public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email) {
        super("El email "+email+" ya existe");
    }
}
