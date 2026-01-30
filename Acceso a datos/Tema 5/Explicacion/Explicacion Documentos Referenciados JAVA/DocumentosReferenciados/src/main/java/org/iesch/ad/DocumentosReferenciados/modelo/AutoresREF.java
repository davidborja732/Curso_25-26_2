package org.iesch.ad.DocumentosReferenciados.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "autores")
public class AutoresREF {
    @Id
    private String id;
    private String nombre;
    private String nacionalidad;

    public AutoresREF(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }
}
