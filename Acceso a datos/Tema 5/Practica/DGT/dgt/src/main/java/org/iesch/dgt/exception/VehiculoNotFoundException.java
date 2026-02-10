package org.iesch.dgt.exception;

/**
 * Excepción cuando no se encuentra un vehículo
 */
public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
