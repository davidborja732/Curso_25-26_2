package com.repaso.datos.Repaso.AD.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nif;
    String nombre;
    String direccion;
    String ciudad;
    int telefono;
    @OneToMany
    @JoinColumn(name = "persona_id")
    List<Coche> coches;
}
