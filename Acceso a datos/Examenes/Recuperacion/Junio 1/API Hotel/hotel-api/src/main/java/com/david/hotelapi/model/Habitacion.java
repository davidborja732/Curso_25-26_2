package com.david.hotelapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("habitaciones")
public class Habitacion {

    @Id
    private String numero;

    private String tipo;
    private int capacidad;
    private double precioNoche;
    private String estado;
}
