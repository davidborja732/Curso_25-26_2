package org.iesch.MongoDemo_Repository.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@NoArgsConstructor
//@AllArgsConstructor
@ToString
@Getter
@Setter
@Document
public class Book {
    private String _id;
    private String titulo;
    private String isbn;
    private Integer anioPublicacion;
    private Double precio;
    private Integer numeroPaginas;
    private String editorial;
    // Documentos embebidos
    private List<Autor> autores;
    private List<String> categorias;

    public Book(String titulo, String isbn, Integer anioPublicacion, List<String> categorias, List<Autor> autores, String editorial, Integer numeroPaginas, Double precio) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.categorias = categorias;
        this.autores = autores;
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
        this.precio = precio;
    }
}
