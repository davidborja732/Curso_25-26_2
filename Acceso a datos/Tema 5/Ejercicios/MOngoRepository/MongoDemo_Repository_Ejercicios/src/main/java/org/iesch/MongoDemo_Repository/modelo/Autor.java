package org.iesch.MongoDemo_Repository.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class Autor {
    private String nombre;
    private String nacionalidad;
}
