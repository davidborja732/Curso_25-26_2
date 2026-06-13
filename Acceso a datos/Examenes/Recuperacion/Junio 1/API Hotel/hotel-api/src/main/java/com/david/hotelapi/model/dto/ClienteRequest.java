package com.david.hotelapi.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    @NotBlank(message = "El DNI es obligatorio")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @Email(message = "El email no tiene un formato válido")
    @NotBlank(message = "El email es obligatorio")
    private String email;
}
