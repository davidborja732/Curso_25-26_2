package com.david.hotelapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("servicios")
public class Servicio {

    @Id
    private String id;

    private String tipoServicio;
    private double precioUnitario;
}
