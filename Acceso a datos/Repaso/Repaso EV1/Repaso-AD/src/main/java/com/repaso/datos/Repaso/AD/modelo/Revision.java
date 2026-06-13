package com.repaso.datos.Repaso.AD.modelo;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Revision {
    String codigo;
    int cambio_filtro;
    int cambio_aceite;
    String coche_id;

}
