package org.iesch.dgt.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Titular de un vehículo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Titular {

    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "Formato de DNI inválido")
    private String dni;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    private LocalDateTime fechaNacimiento;

    @Email(message = "Email no válido")
    private String email;

    @Pattern(regexp = "^[0-9]{9}$", message = "Teléfono debe tener 9 dígitos")
    private String telefono;

    private Domicilio domicilioFiscal;
}
