package org.iesch.ad.demoValid.modelo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Size(min = 3,max = 25,message = "El nombre debe tener de 3 a 25 caracteres ")
    private String nombre;
    @NotBlank(message = "El email no puede estar vacio.")
    private String email;
}
