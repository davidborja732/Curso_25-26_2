package org.iesch.ad.DocumentosReferenciados.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "libros_REF")
public class BookRef {

    @Id
    private String id;
    private String titulo;
    private String isbn;
    private Integer anioPublicacion;
    private Double precio;
    private Integer numeroPaginas;
    private String editorial;

    //Documentos embebidos
    @DBRef
    private List<AutoresREF> autores;
    private List<String> categorias;

    public BookRef(String titulo, String isbn, Integer anioPublicacion, Double precio, Integer numeroPaginas, String editorial, List<AutoresREF> autores, List<String> categorias) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.precio = precio;
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.autores = autores;
        this.categorias = categorias;
    }
}
