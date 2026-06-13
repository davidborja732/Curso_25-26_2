package org.iesch.proyectobase.proyectobase.excepciones;

import org.iesch.proyectobase.proyectobase.modelo.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ManejadorGlobal {
    //Maneja la excepcion cuando no se encuentra el estudiante ademas devolvera un 404
    @ExceptionHandler(EstudianteNoEncontradoExcepcion.class)
    public ResponseEntity<ErrorResponseDTO> manejarEstudinateNoEncobntrado(EstudianteNoEncontradoExcepcion excepcion, WebRequest request){
        ErrorResponseDTO error=new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "No encontrado en el sistema",
                excepcion.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    // Devolvera HTTP 409 Conflict
    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErrorResponseDTO> manejarEmailDuplicado(EmailDuplicadoException excepcion, WebRequest request){
        ErrorResponseDTO error=new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Ya existe en el sistema",
                excepcion.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
    //
    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<ErrorResponseDTO> manejarDatosInvalidos(DatosInvalidosException excepcion, WebRequest request){
        ErrorResponseDTO error=new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "El dato es invalido",
                excepcion.getMessage(),
                request.getDescription(false).replace("uri=","")
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    /*
    Manejar excepciones validadas por @Valid
    usaremos la lista

    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> manejarValid(MethodArgumentNotValidException excepcion, WebRequest request){
        List<String> detalles=new ArrayList<>();
        // Extaremos errores de validacion
        for (FieldError error:excepcion.getBindingResult().getFieldErrors()){
            detalles.add(error.getField()+": "+error.getDefaultMessage());
        }
        ErrorResponseDTO error=new ErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "El dato es invalido",
                excepcion.getMessage(),
                request.getDescription(false).replace("uri=",""),
                detalles
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
