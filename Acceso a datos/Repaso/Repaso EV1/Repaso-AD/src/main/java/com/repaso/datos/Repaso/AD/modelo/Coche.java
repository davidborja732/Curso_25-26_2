package com.repaso.datos.Repaso.AD.modelo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Coche {
    String matricula;
    String marca;
    String modelo;
    String color;
    String precio;
    Long cliente_id;
}
