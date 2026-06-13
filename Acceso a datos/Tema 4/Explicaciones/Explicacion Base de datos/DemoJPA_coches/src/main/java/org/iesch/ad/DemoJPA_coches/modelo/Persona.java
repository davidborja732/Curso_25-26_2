package org.iesch.ad.DemoJPA_coches.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nombre;
    String apellido;
    /*@OneToOne
    Coche coche;*/
    @OneToMany
    @JoinColumn(name = "persona_id")
    List<Coche> coches;
}
