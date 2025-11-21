package org.iesch.ad.NconsultasYmas.modelo;

import jakarta.persistence.*;
import lombok.*;

@Data// Crea getter,setter,tostring y mas
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "libros")//cambio el nombre de la tabla al que yo le diga
/*
@Getter
@Setter
@ToString
 */
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private String isbn;
    private Double precio;
    @Column(name="anio_publicacion")
    private Integer anioPublicacion;

    // Por defecto esto sera EAGER lo puedo forzar para que sea LAZY
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="autor_id",nullable = false)
    private Autor autor;

    public Libro(String titulo, String isbn, Double precio, Integer anioPublicacion, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }
}
