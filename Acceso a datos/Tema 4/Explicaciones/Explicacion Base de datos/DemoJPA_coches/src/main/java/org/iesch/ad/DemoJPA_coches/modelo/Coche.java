package org.iesch.ad.DemoJPA_coches.modelo;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@ToString
@Entity
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private float cilindrada;
    private int potencia;
    private String matricula;
    private String color;
    @ManyToOne
    Persona persona;

    @Override
    public String toString() {
        return "Coche{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cilindrada=" + cilindrada +
                ", potencia=" + potencia +
                ", matricula='" + matricula + '\'' +
                ", color='" + color + '\'' +
                ", persona=" + persona.getId() +
                '}';
    }
}
