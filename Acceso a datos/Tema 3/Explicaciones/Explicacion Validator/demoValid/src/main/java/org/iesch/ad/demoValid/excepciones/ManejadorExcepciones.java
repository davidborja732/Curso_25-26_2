package org.iesch.ad.demoValid.excepciones;

import org.iesch.ad.demoValid.modelo.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarDatosErroneos(MethodArgumentNotValidException ex){
        String mensaje="Error de validacion " + ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponseDTO errorResponseDTO=new ErrorResponseDTO(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), mensaje);
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.BAD_REQUEST);
    }
}
