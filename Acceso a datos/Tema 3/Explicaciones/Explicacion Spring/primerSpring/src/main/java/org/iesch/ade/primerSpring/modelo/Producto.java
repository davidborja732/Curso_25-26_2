package org.iesch.ade.primerSpring.modelo;

import lombok.*;
//@Data

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Builder
public class Producto {
    int id;
    String nombre;
    float precio;
}
