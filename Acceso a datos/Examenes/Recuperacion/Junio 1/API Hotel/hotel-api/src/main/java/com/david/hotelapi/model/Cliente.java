package com.david.hotelapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("clientes")
public class Cliente {

    @Id
    private String dni;

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String fechaRegistro;
}
