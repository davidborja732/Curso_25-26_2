package org.iesch.proyectobase.proyectobase.excepciones;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email) {
        super("El email "+email+" ya existe");
    }
}
