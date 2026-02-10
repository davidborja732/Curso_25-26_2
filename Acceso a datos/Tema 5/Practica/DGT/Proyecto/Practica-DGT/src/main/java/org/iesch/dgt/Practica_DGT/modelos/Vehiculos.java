package org.iesch.dgt.Practica_DGT.modelos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "dgt")
public class Vehiculos {
    @Id
    private String id;
    private String matricula;
    private String bastidor;
    private String marca;
    private String modelo;
    private String color;
    private String tipoVehiculo;
    private String anioFabricacion;
}
