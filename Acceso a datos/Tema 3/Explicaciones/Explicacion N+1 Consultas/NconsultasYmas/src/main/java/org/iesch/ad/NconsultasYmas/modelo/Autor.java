package org.iesch.ad.NconsultasYmas.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autores")//cambio el nombre de la tabla al que yo le diga
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellido;
    private String nacionalidad;

    // Por defecto es un comportamiento LAZY(vago) -> Aparace el problema n+1 consultas
    @OneToMany(mappedBy = "autor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)// Puedo cambiar el tipo de comportamiento
    private List<Libro> libros=new ArrayList<>();

    public Autor(String nombre, String apellido, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
    }
}
