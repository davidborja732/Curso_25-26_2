package org.iesch.MongoDemo_Repository.excepciones;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ManejadorGlobal {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> errorescontrol(MethodArgumentNotValidException ex){
        List<Map<String, String>> errores=ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
        {
            Map<String, String> erroresstream =new HashMap<>();
            erroresstream.put("Campo",fieldError.getField());
            erroresstream.put("Mensaje",fieldError.getDefaultMessage());

            return erroresstream;
        }
        ).toList();
        return ResponseEntity.badRequest().body(errores);
    }
}
