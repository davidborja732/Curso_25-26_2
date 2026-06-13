package com.david.hotelapi.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaRequest {

    @NotBlank(message = "El DNI del cliente es obligatorio")
    private String dniCliente;

    @NotBlank(message = "El número de habitación es obligatorio")
    private String numeroHabitacion;

    @NotBlank(message = "La fecha de entrada es obligatoria")
    private String fechaEntrada;

    @NotBlank(message = "La fecha de salida es obligatoria")
    private String fechaSalida;

    @Positive(message = "El número de personas debe ser mayor que 0")
    private int numeroPersonas;

    @NotBlank(message = "El régimen es obligatorio")
    private String regimen;

    @NotBlank(message = "La temporada es obligatoria")
    private String temporada;

    @Valid
    private List<ServicioAdicionalRequest> serviciosAdicionales;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServicioAdicionalRequest {

        @NotBlank(message = "El ID del servicio es obligatorio")
        private String id;

        @Positive(message = "La cantidad debe ser mayor que 0")
        private int cantidad;
    }
}
