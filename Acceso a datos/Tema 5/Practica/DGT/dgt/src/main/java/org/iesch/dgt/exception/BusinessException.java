package org.iesch.dgt.exception;

/**
 * Excepción para errores de lógica de negocio
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String mensaje) {
        super(mensaje);
    }

    public BusinessException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
