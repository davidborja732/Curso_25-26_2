package org.iesch.proyectobase.proyectobase.modelo.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
    private String patch;
    private List<String> detalles;


    public ErrorResponseDTO(LocalDateTime timeStamp, int status, String error, String message, String patch) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.patch = patch;
    }
}
